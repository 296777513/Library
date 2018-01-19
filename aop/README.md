
在[AOP在Android中最佳用法]()中介绍了AOP的几种实践方式，在实现动态权限检查、性能检测、埋点等操作时建议使用的是AspectJ方式。本文介绍**使用AspectJ一行代码实现权限检查问题**。

# Android系统权限机制

首先简单介绍一下Android中的权限机制

## Android 6.0之前的权限

* 在`AndroidManifest.xml`中声明需要用到的权限。
* 用户在安装时，系统展示需要申请的权限，用户暗中即授予所有的权限，取消则拒绝安装。


## Android 6.0 及其之后的权限-运行时权限

申请步骤

1. 在`AndroidManifest.xml`中声明需要使用到的权限。
2. 使用前检查
3. 申请权限
4. 处理申请回调

## Android 6.0运行时权限分类

* 危险权限

危险权限即需要动态申请的权限，一共9组，获取一组中的某个授权，则自动拥有该组的所有授权

名称 | 总权限| 所有权限
:---|:---|:---
通讯录 | CONTACTS | WRITE_CONTACTS、GET_ACCOUNTS、READ_CONTACTS
电话 | PHONE|READ_CALL_LOG、READ_PHONE_STATE、CALL_PHONE、WRITE_CALL_LOG、USE_SIP、PROCESS_OUTGOING_CALLS、ADD_VOICEMAIL
日历| CALENDAR|READ_CALENDAR|WRITE_CALENDAR
相机|CAMERA|CAMERA
传感器|SENSORS|BODY_SENSORS
地理位置|LOCATION|ACCESS_FINE_LOCATION、ACCESS_COARSE_LOCATION
存储空间|STORAGE|READ_EXTERNAL_STORAGE、WRITE_EXTERNAL_STORAGE
麦克风|MICROPHONE|RECORD_AUDIO
短信|SMS|READ_SMS、RECEIVE_WAP_PUSH、RECEIVE_MMS、RECEIVE_SMS、SEND_SMS、READ_CELL_BROADCASTS

* 普通权限

除上面的危险权限之外的权限即为一般权限（普通权限）

## Android 6.0权限申请API

 API方法| 说明
:-------- |:---
`int checkSelfPermission(@NonNull Context context, @NonNull String permission)` |可用`activity.checkSelfPermission`或者v4包下的 `ContextCompat.checkSelfPermission`来检查权限是否已授权
 `void requestPermissions(final @NonNull Activity activity,final @NonNull String[] permissions, final @IntRange(from = 0) int requestCode)` | 可用`activity.requestPermissions`或者v4包下的 `ContextCompat.requestPermissions`来进行权限申请。需要为每一个权限指定一个id，当系统返回授权结果时，应用根据id拿到授权结果。
`void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,@NonNull int[] grantResults)` | 由系统自动触发，当应用申请权限后，Activity将触发这个回调，告诉应用用户的授权结果。
`boolean shouldShowRequestPermissionRationale(@NonNull Activity activity, @NonNull String permission)` |当应用首次申请权限时，如果用户点击拒绝，下次再申请权限，Android允许你提示用户，你为什么需要这个权限，更好引导用户是否授权，其中在Android原生系统中：如果应用之前请求过此权限但用户拒绝了请求，此方法将返回true；如果用户在过去拒绝了权限请求且在权限请求系统对话框中选择了 Don't ask again 选项将返回 false；如果第一次申请权限也返回false；如果设备规范禁止应用具有该权限，此方法也会返回 false，返回false则不在显示提示对话框，返回true则回显示对话框（但并不一定任何系统的机制都是如此，不同厂商不同的Rom机制有可能不同，以HTC 6.0和联想的系统为例，在HTC上就不一定是返回true，目前测试结果一直都是false，而联想手机上则和原生的机制一样）


# 使用AspectJ封装权限检查

## 1. 导入AspectJ

首先，需要在项目根目录的build.gradle中添加依赖

```java
buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:2.3.0-beta2'
        classpath 'com.hujiang.aspectjx:gradle-android-plugin-aspectjx:1.0.10'

    }
}
```
然后，在module的build.gradle中添加AspectJ模块

```java
apply plugin: 'android-aspectjx'
```

最后，在module的build.gradle中添加AspectJ依赖

```java
compile 'org.aspectj:aspectjrt:1.8.10'
```

## 2. 启动的application继承下面的Application

```java

public class BaseApplication extends Application {
    private static BaseApplication mApp;
    public Stack<Activity> store;

    public void onCreate() {
        super.onCreate();
        mApp = this;
        store = new Stack<>();
        registerActivityLifecycleCallbacks(new SwitchBackgroundCallbacks());
    }

    public static BaseApplication getAppContext() {
        return mApp;
    }


    private class SwitchBackgroundCallbacks implements Application.ActivityLifecycleCallbacks {

        @Override
        public void onActivityCreated(Activity activity, Bundle bundle) {
            store.add(activity);
        }

        @Override
        public void onActivityStarted(Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {

        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            store.remove(activity);
        }
    }

    /**
     * 获取当前的Activity
     *
     * @return
     */
    public Activity getCurActivity() {
        return store.lastElement();
    }
}
```

## 3. 创建自己的权限注解类和AOP类

权限注解类

```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Permission {
    String[] value();
}
```

AOP类，其中PermissionUtils类是封装好的检查权限的类。

```java
@Aspect
public class PermissionAspectj {
    @Around("execution(@com.knight.basetools.annotation.Permission * *(..)) && @annotation(permission)")
    //在所有 有Permission的方法替换代码
    public void aroundJoinPoint(final ProceedingJoinPoint joinPoint, final Permission permission) throws Throwable {
        final AppCompatActivity ac = (AppCompatActivity) BaseApplication.getAppContext().getCurActivity();
        if (PermissionUtils.checkPermissions(ac, permission.value())) {//如果有权限，则直接执行原方法
            try {
                joinPoint.proceed();
            } catch (Throwable e) {
                e.printStackTrace();
            }
        } else {
            new AlertDialog.Builder(ac)
                    .setTitle(R.string.title)
                    .setMessage(R.string.permission_describe)
                    .setNegativeButton(R.string.negative, null)
                    .setPositiveButton(R.string.positive, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            PermissionUtils.requestPermissionsResult(ac, 1, permission.value()
                                    , new PermissionUtils.OnPermissionListener() {
                                        @Override
                                        public void onPermissionGranted() {
                                            try {
                                                joinPoint.proceed();//获得权限，执行原方法
                                            } catch (Throwable e) {
                                                e.printStackTrace();
                                            }
                                        }

                                        @Override
                                        public void onPermissionDenied() {
                                            PermissionUtils.showTipsDialog(ac);
                                        }
                                    });
                        }
                    })
                    .create()
                    .show();
        }
    }
}
```

## 4. 在项目中使用

举个简单例子，如果需要打开系统的摄像机，那么只需要一行代码就可以：

```java
 @Permission(Manifest.permission.CAMERA)
    private void onCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);// 启动系统相机
        startActivityForResult(intent, 1);
    }
```
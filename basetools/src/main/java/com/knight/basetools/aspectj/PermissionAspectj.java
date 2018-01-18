package com.knight.basetools.aspectj;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.knight.basetools.BaseApplication;
import com.knight.basetools.R;
import com.knight.basetools.annotation.Permission;
import com.knight.basetools.utils.PermissionUtils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * description
 *
 * @author liyachao
 * @date 2018/1/18
 */
@Aspect
public class PermissionAspectj {
    @Around("execution(@com.knight.basetools.annotation.Permission * *(..)) && @annotation(permission)")
//在所有 有Permission的方法替换代码
    public void aroundJoinPoint(final ProceedingJoinPoint joinPoint, final Permission permission) throws Throwable {
        final AppCompatActivity ac = (AppCompatActivity) BaseApplication.getAppContext().getCurActivity();
        if (PermissionUtils.checkPermissions(ac, permission.value())) {
            try {
                joinPoint.proceed();//获得权限，执行原方法
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

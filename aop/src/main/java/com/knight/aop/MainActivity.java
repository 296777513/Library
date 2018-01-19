package com.knight.aop;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.knight.basetools.annotation.Permission;
import com.knight.basetools.utils.PermissionUtils;

import hugo.weaving.DebugLog;


public class MainActivity extends BaseActivity implements View.OnClickListener {
    public static final String TAG = MainActivity.class.getSimpleName();

    private Button mPhoneCallBtn;
    private Button mCameraBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getName("li", "yachao");
        Log.i(TAG, "onCreate");
        mPhoneCallBtn = findViewById(R.id.button);
        mCameraBtn = findViewById(R.id.button1);
        mPhoneCallBtn.setOnClickListener(this);
        mCameraBtn.setOnClickListener(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }

    public String getName(String first, String last) {
        SystemClock.sleep(15);
        return first + " " + last;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                onCall();
                break;
            case R.id.button1:
                onCamera();
                break;
        }
    }

    @Permission(Manifest.permission.CAMERA)
    private void onCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);// 启动系统相机
        startActivityForResult(intent, 1);
    }

    @Permission(Manifest.permission.CALL_PHONE)
    private void onCall() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + "13800000000");
        intent.setData(data);
        startActivity(intent);
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        PermissionUtils.onRequestPermissionsResult(requestCode,permissions,grantResults);
//    }
}

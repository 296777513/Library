package com.knight.aop;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.knight.basetools.utils.PermissionUtils;

/**
 * description
 *
 * @author liyachao
 * @date 2018/1/19
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionUtils.onRequestPermissionsResult(requestCode,permissions,grantResults);
    }
}

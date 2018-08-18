package com.knight.aop;


import com.knight.basetools.utils.PermissionUtils;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import hugo.weaving.DebugLog;

/**
 * description
 *
 * @author liyachao
 * @date 2018/1/19
 */
@DebugLog
public class BaseActivity extends AppCompatActivity {

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionUtils.onRequestPermissionsResult(requestCode,permissions,grantResults);
    }
}

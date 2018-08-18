package com.knight.aop.lancet;

import android.os.Bundle;
import android.util.Log;

import com.knight.aop.MainActivity;

import androidx.annotation.NonNull;

/**
 * description
 *
 * @author liyachao
 * @date 2018/1/17
 */

public class LancetActivity {

//    @Insert("onCreate")
//    @TargetClass("com.knight.aop.MainActivity")
//    public void onCreate(Bundle savedInstanceState) {
//        long startTime = System.currentTimeMillis();
//        Origin.callVoid();
//        Class<?> aClass = null;
//        try {
//            aClass = Class.forName("com.knight.aop.MainActivity");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//            aClass = MainActivity.class;
//        }
//        Log.i(aClass.getSimpleName(), "lancet onCreate waste time : " + (System.currentTimeMillis() - startTime));
//    }
//
//    @Insert(value = "onStart",mayCreateSuper = true)
//    @TargetClass("com.knight.aop.MainActivity")
//    public void onStart(){
//        long startTime = System.currentTimeMillis();
//        Origin.callVoid();
//        Class<?> aClass = null;
//        try {
//            aClass = Class.forName("com.knight.aop.MainActivity");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//            aClass = MainActivity.class;
//        }
//        Log.i(aClass.getSimpleName(), "lancet onStart waste time : " + (System.currentTimeMillis() - startTime));
//    }
//
//    @Insert(value = "onRequestPermissionsResult",mayCreateSuper = true)
//    @TargetClass("com.knight.aop.MainActivity")
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
//        long startTime = System.currentTimeMillis();
//        Origin.callVoid();
//        Class<?> aClass = null;
//        try {
//            aClass = Class.forName("com.knight.aop.MainActivity");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//            aClass = MainActivity.class;
//        }
//        Log.i(aClass.getSimpleName(), "lancet onRequestPermissionsResult waste time : " + (System.currentTimeMillis() - startTime));
//    }
}

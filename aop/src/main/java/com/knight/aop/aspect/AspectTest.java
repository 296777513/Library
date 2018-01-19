package com.knight.aop.aspect;

import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * description
 *
 * @author liyachao
 * @date 2018/1/17
 */

@Aspect
public class AspectTest {

    @Before("execution(* android.support.v7.app.AppCompatActivity.on**(..))")
    public void onActivityMethodBefore(JoinPoint joinPoint) throws Throwable {
        Signature signature = joinPoint.getSignature();

        Class<?> cls = signature.getDeclaringType();
        String key = joinPoint.getSignature().toString();
        Log.i(asTag(cls), "onActivityMethodBefore: " + key);
    }

    @Before("execution(* com.knight.aop.MainActivity.onRequestPermissionsResult(..))")
    public void ononRequestPermissionsResultBefore(JoinPoint joinPoint) throws Throwable {
        Signature signature = joinPoint.getSignature();

        Class<?> cls = signature.getDeclaringType();
        String key = joinPoint.getSignature().toString();
        Log.i(asTag(cls), "onRequestPermissionsResult: " + key);
    }

    private static String asTag(Class<?> cls) {
        if (cls.isAnonymousClass()) {
            return asTag(cls.getEnclosingClass());
        }
        return cls.getSimpleName();
    }
}

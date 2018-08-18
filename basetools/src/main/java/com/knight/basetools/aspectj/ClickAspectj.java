package com.knight.basetools.aspectj;

import android.util.Log;
import android.widget.Toast;


import com.knight.basetools.annotation.Clickable;
import com.knight.basetools.tools.GlobalContext;
import com.knight.basetools.utils.ClickUtils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * description
 *
 * @author liyachao
 * @date 2018/8/15
 */
@Aspect
public class ClickAspectj {

    @Around("execution(@com.knight.basetools.annotation.Clickable * *(..)) && @annotation(clickable)")
    public void aroundJoinPoint(final ProceedingJoinPoint joinPoint, final Clickable clickable) throws Throwable {
        Log.i("liyachao", "start1");
        if (ClickUtils.INSTANCE.clickEnable(clickable.value())) {
            Log.i("liyachao", "可以点击");
            try {
                joinPoint.proceed();
            } catch (Throwable e) {
                e.printStackTrace();
            }
        } else if (clickable.isNeedToast()) {
            Log.i("liyachao", "禁止点击");
            Toast.makeText(GlobalContext.INSTANCE.getContext(), "点击太快了~", Toast.LENGTH_LONG).show();
        }
    }

}

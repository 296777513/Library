package com.knight.aop.lancet;

import me.ele.lancet.base.Origin;
import me.ele.lancet.base.annotations.Proxy;
import me.ele.lancet.base.annotations.TargetClass;

/**
 * description
 *
 * @author liyachao
 * @date 2018/1/17
 */

public class LancetLog {
    @Proxy("i")
    @TargetClass("android.util.Log")
    public static int i(String tag,String msg){
        msg = msg+ " lancet";
        return (int)Origin.call();
    }

}

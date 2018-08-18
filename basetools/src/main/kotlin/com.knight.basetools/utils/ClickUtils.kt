package com.knight.basetools.utils

/**
 * description
 *
 * @author liyachao
 * @date 2018/8/17
 */
object ClickUtils {
    private const val MIN_DELAY_TIME = 1000  // 两次点击间隔不能少于1000ms
    private var lastClickTime: Long = 0

    fun clickEnable(time: Int): Boolean {
        var delayTime = time
        var flag = false
        delayTime = if (delayTime <= 500) MIN_DELAY_TIME else delayTime
        val currentClickTime = System.currentTimeMillis()
        if (currentClickTime - lastClickTime >= delayTime) {
            flag = true
        }
        lastClickTime = currentClickTime
        return flag
    }
}
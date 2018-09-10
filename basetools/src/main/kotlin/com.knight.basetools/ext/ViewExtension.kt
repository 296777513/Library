package com.knight.basetools.ext

import android.view.View
import com.knight.basetools.ext.TriggerTime.DELAY_TIME

/**
 * description
 *
 * @author liyachao
 * @date 2018/9/3
 */
object TriggerTime {
    var triggerLastTime: Long = 0
    const val DELAY_TIME = 300
}

inline fun <T : View> T.clickWithAnimation(onClickListener: View.OnClickListener) = setOnClickListener {
    if (clickEnable) {
        it.animate().scaleX(0.8f).scaleY(0.8f).setDuration(80).withEndAction {
            it.animate().scaleX(1f).scaleY(1f).setDuration(80).start()
        }.start()
        onClickListener.onClick(it)
    }
}

inline fun <T : View> T.clickWithEvent(hashMap: HashMap<String, String>, noinline block: (T) -> Unit) {
    // hashmap 进行打点
    setOnClickListener {
        if (clickEnable)
            block.invoke(it as T)
    }
}

var <T : View> T.clickEnable: Boolean
    get() {
        var flag = false
        val currentClickTime = System.currentTimeMillis()
        if (currentClickTime - TriggerTime.triggerLastTime >= DELAY_TIME) {
            flag = true
        }
        TriggerTime.triggerLastTime = currentClickTime
        return flag
    }
    set(value) {

    }
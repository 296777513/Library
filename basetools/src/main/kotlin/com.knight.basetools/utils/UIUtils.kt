package com.knight.basetools.utils

import android.content.Context
import android.view.View
import android.view.ViewTreeObserver
import com.knight.basetools.tools.GlobalContext

/**
 * description
 *
 * @author liyachao
 * @date 2018/8/14
 */
object UIUtils {

    inline fun getScreenHeight(): Int = GlobalContext.context.resources.displayMetrics.heightPixels
            ?: 0

    inline fun getScreenWidth(): Int = GlobalContext.context.resources.displayMetrics.widthPixels
            ?: 0


    inline fun dip2Px(dipValue: Float): Float = dipValue / GlobalContext.context.resources.displayMetrics.density + 0.5f

    fun waitForMeasure(view: View, callBack: (View, Int, Int) -> Unit) {
        view.run {
            val width = width
            val height = height
            if (width > 0 && height > 0) {
                callBack(view, width, height)
                return
            }
            viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    if (viewTreeObserver.isAlive) {
                        viewTreeObserver.removeOnPreDrawListener(this)
                    }
                    callBack(view, view.width, view.height)
                    return true
                }
            })
        }
    }
}



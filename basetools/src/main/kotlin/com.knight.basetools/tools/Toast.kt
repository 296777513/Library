package com.knight.basetools.tools

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/**
 * description
 *
 * @author liyachao
 * @date 2018/8/14
 */


fun Toast.setGravityCenter(): Toast {
    setGravity(Gravity.CENTER, 0, 0)
    return this
}

/**
 * 设置Toast字体已经背景颜色
 */
//fun Toast.setToastColor(@ColorInt messageColor: Int, @ColorInt backgroundColor: Int) {
//    view?.let {
//        val message = view.findViewById(android.R.id.message) as TextView
//        message.setBackgroundColor(backgroundColor)
//        message.setTextColor(messageColor)
//    }
//}
//
//fun Toast.setBackgrounnd(@ColorInt messageColor: Int = Color.WHITE
//                         , @DrawableRes background: Int = android.R.drawable.toast_frame): Toast {
//    view?.let {
//        val message = view.findViewById(android.R.id.message) as TextView
//        message.setBackgroundResource(background)
//        message.setTextColor(messageColor)
//    }
//    return this
//}

fun toast(text: CharSequence): Toast = Toast.makeText(GlobalContext.context, text
        , Toast.LENGTH_LONG).setGravityCenter()

fun toast(@StringRes res: Int): Toast = Toast.makeText(GlobalContext.context, GlobalContext.context.resources.getString(res)
        , Toast.LENGTH_LONG).setGravityCenter()


data class ToastWrapper(var text: String? = null, var res: Int? = null,
                        var showSuccess: Boolean = false, var showError: Boolean = false)

fun toast(init: ToastWrapper.() -> Unit) {
    val wrap = ToastWrapper()

    wrap.init()

    execute(wrap)
}

private fun execute(wrap: ToastWrapper) {

    var taost: Toast? = null

    wrap.text?.run {
        taost = toast(this)
        return@run wrap
    }?.res?.let {
        taost = toast(it)
    }

//    if (wrap.showSuccess) {
//
//        taost?.withSuccIcon()
//    } else if (wrap.showError) {
//
//        taost?.withErrorIcon()
//    }

    taost?.show()
}


fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun Context.toast(message: Int, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, this.resources.getString(message), duration).show()
}

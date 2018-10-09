package com.knight.basetools.ext

import android.content.Context
import com.knight.basetools.tools.GlobalContext

/**
 * description
 *
 * @author liyachao
 * @date 2018/9/11
 */
val Context.screenHeight: Int
    get() = this.resources.displayMetrics.heightPixels


val Context.screenWidth: Int
    get() = this.resources.displayMetrics.widthPixels

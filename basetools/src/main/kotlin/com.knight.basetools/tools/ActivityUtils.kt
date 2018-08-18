package com.knight.basetools.tools

import android.app.Activity
import java.util.*

/**
 * description
 *
 * @author liyachao
 * @date 2018/8/15
 */
object ActivityUtils {
    val store: Stack<Activity> by lazy {
        Stack<Activity>()
    }

    fun getCurActivity(): Activity {
        return store.lastElement()
    }
}
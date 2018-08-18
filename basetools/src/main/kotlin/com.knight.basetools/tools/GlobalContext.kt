package com.knight.basetools.tools

import android.content.Context
import kotlin.properties.Delegates

/**
 * description
 *
 * @author liyachao
 * @date 2018/8/14
 */
object GlobalContext {
    var context: Context by Delegates.notNull<Context>()
}
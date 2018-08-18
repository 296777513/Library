package com.knight.basetools.tools

/**
 * description
 *
 * @author liyachao
 * @date 2018/8/15
 */
fun String.isBlank(): Boolean {
    this.forEach {
        if (!Character.isWhitespace(it)) {
            return false
        }
    }
    return true
}
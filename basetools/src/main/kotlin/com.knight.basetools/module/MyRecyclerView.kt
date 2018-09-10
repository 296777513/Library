package com.knight.basetools.module

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView

/**
 * description
 *
 * @author liyachao
 * @date 2018/9/6
 */
class MyRecyclerView : RecyclerView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)

    init {
        addOnScrollListener(GlideRecyclerViewScrollListener(context))
    }
}
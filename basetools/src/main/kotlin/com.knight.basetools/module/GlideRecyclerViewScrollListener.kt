package com.knight.basetools.module

import android.content.Context
import android.view.ViewConfiguration
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

/**
 * description
 *
 * @author liyachao
 * @date 2018/9/6
 */
class GlideRecyclerViewScrollListener(val context: Context) : RecyclerView.OnScrollListener() {
    private val touchSlop: Int = ViewConfiguration.get(context).scaledTouchSlop


    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            Glide.with(context).resumeRequests()
        }
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val mdy = Math.abs(dy)
        if (recyclerView.scrollState == RecyclerView.SCROLL_STATE_DRAGGING && mdy < touchSlop) {
            Glide.with(context).resumeRequests()
        } else if (recyclerView.scrollState == RecyclerView.SCROLL_STATE_DRAGGING && mdy >= touchSlop) {
            Glide.with(context).pauseAllRequests()
        } else if (recyclerView.scrollState == RecyclerView.SCROLL_STATE_SETTLING) {
            Glide.with(context).pauseAllRequests()
        }
    }

}
package com.knight.basetools.ext

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.request.RequestOptions
import com.knight.basetools.R
import com.knight.basetools.module.GlideApp
import com.knight.basetools.module.GlideRequest
import org.jetbrains.anko.dip
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions.withCrossFade
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions


/**
 * description
 *
 * @author liyachao
 * @date 2018/9/4
 */

val roundedOptions = RequestOptions.bitmapTransform(RoundedCorners(6))
val circleOptions = RequestOptions.circleCropTransform()
inline fun ImageView.load(url: String) {
    get(url).placeholder(R.drawable.icon_default)
            .error(R.drawable.icon_default)
            .into(this)
}

/**
 * 占位符圆角矩形
 */
inline fun ImageView.loadRound(url: String) {
    get(url).placeholder(R.drawable.icon_default)
            .error(R.drawable.icon_default)
            .apply(roundedOptions)
//            .transform(RequestOptions.bitmapTransform(DisplayUtil.dp2px(context, 6f), 0))
            .into(this)
}

/**
 * 占位符圆形
 */
inline fun ImageView.loadCircle(url: Drawable) {
    get(url).placeholder(R.drawable.icon_default)
            .error(R.drawable.icon_default)
            .transition(DrawableTransitionOptions().crossFade())
            .apply(circleOptions)
            .into(this)
}

/**
 * 占位符圆形
 */
inline fun ImageView.loadCircleThumbnail(url: String) {
    get(url).placeholder(R.drawable.icon_default)
            .error(R.drawable.icon_default)
            .transition(DrawableTransitionOptions().crossFade())
            .apply(circleOptions)
            .thumbnail(0.5f)
            .into(this)
}

inline fun ImageView.loadCircle(url: String) {
    get(url).placeholder(R.drawable.icon_default)
            .error(R.drawable.icon_default)
            .apply(circleOptions)
            .into(this)
}

inline fun ImageView.clear() {
    GlideApp.with(context).clear(this)
}


inline fun ImageView.get(url: String): GlideRequest<Drawable> = GlideApp.with(context).asDrawable().load(url)

inline fun ImageView.get(url: Drawable): GlideRequest<Drawable> = GlideApp.with(context).asDrawable().load(url)
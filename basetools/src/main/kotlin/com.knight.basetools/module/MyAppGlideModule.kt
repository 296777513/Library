package com.knight.basetools.module

import android.content.Context
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory
import com.bumptech.glide.GlideBuilder


/**
 * description
 *
 * @author liyachao
 * @date 2018/9/4
 */
@GlideModule
class MyAppGlideModule : AppGlideModule() {

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        /*MemorySizeCalculator calculator = new MemorySizeCalculator.Builder(context) .setMemoryCacheScreens(2) .build();
        builder.setMemoryCache(new LruResourceCache(calculator.getMemoryCacheSize()));*/
        val diskCacheSizeBytes = 1024 * 1024 * 60 // 100 MB
        builder.setDiskCache(InternalCacheDiskCacheFactory(context, diskCacheSizeBytes.toLong()))


    }

    override fun isManifestParsingEnabled(): Boolean {
        return false
    }
}
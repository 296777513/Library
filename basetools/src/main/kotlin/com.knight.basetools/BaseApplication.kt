package com.knight.basetools

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Log
import com.knight.basetools.tools.ActivityUtils
import com.knight.basetools.tools.GlobalContext
import com.knight.basetools.tools.Tool
import com.knight.basetools.utils.ToastUtil
import java.util.*
import kotlin.properties.Delegates

/**
 * description
 *
 * @author liyachao
 * @date 2018/8/14
 */
open class BaseApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        Log.i("liyachao","123")
        registerActivityLifecycleCallbacks(SwitchBackgroundCallbacks())
        GlobalContext.context = this
//        Tool.init(this)
    }


    private inner class SwitchBackgroundCallbacks : ActivityLifecycleCallbacks {
        override fun onActivityPaused(activity: Activity?) {
        }

        override fun onActivityResumed(activity: Activity?) {
        }

        override fun onActivityStarted(activity: Activity?) {
        }

        override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
        }

        override fun onActivityStopped(activity: Activity?) {
        }

        override fun onActivityDestroyed(activity: Activity?) {
            ActivityUtils.store.remove(activity)
        }

        override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
            ActivityUtils.store.add(activity)
        }
    }
}
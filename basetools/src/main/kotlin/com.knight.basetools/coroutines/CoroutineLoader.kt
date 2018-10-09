package com.knight.basetools.coroutines

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.UI
import org.jetbrains.anko.AnkoLogger

/**
 * description
 *
 * @author liyachao
 * @date 2018/9/4
 */
internal class CoroutineLifecycleListener(private val deferred: Deferred<*>) : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        if (!deferred.isCancelled) {
            deferred.cancel()
        }
    }
}

fun <T> LifecycleOwner.postDelay(delay: Long, block: suspend (T) -> Unit): Unit {
    val deferred = async(context = UI) {
        delay(delay)
        block
    }
    lifecycle.addObserver(CoroutineLifecycleListener(deferred))
}

fun <T> LifecycleOwner.load(loader: suspend () -> T): Deferred<T> {
    val deferred = async(CommonPool, start = CoroutineStart.LAZY) {
        loader()
    }

    lifecycle.addObserver(CoroutineLifecycleListener(deferred))
    return deferred
}

infix fun <T> Deferred<T>.then(block: suspend (T) -> Unit): Job {
    return launch(UI) {
        try {
            block(this@then.await())
        } catch (e: Exception) {
            AnkoLogger(e.toString())
            throw  e
        }
    }
}
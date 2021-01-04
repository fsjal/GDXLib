package com.lib.gdx.util

import com.badlogic.gdx.utils.Timer

inline fun schedule(delay: Float, crossinline bloc: () -> Unit): Timer.Task =
        Timer.schedule(object : Timer.Task() { override fun run() = bloc() }, delay)

inline fun <reified T> Lazy(crossinline block: () -> T) = lazy(LazyThreadSafetyMode.NONE) { block() }
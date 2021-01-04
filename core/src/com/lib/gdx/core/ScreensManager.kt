package com.lib.gdx.core

import com.badlogic.gdx.graphics.g2d.Batch
import java.util.*

interface ScreensManager {

    val screens: Stack<Screen>
    val batch: Batch
    val current: Screen
    val size: Int

    fun push(newScreen: Screen)

    fun pop()

    operator fun get(index: Int): Screen
}
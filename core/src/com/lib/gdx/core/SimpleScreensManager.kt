package com.lib.gdx.core

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import java.util.*

class SimpleScreensManager(disposablesManager: DisposablesManager) :
        ScreensManager,
        DisposablesManager by disposablesManager {

    override val screens by lazy { Stack<Screen>() }
    override val batch by lazy { SpriteBatch() }
    override val current: Screen get() = screens.peek()
    override val size get() = screens.size

    init {
        println("Screen dispose add")
        disposables += this
    }

    /**
     * Push a screen to the manager and show it
     * @param newScreen
     */
    override fun push(newScreen: Screen) {
        if (size != 0) current.hide()
        screens.push(newScreen)
        newScreen.create()
        newScreen.show()
        newScreen.resize(Gdx.graphics.width, Gdx.graphics.height)
    }

    /**
     * Hide and dispose of the current screen, return to the previous one
     */
    override fun pop() {
        current.hide()
        current.dispose()
        screens.pop()
        current.show()
        current.resize(Gdx.graphics.width, Gdx.graphics.height)
    }

    /**
     * Get the screen at specific index
     * @param index
     */
    override operator fun get(index: Int): Screen = screens[index]

    /**
     * Dispose all screens and clear them from the manager
     */
    override fun dispose() {
        println("game dispose")
        screens.forEach { it.dispose() }
        screens.clear()
        batch.dispose()
    }
}
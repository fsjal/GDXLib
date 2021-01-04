package com.lib.gdx

import com.badlogic.gdx.graphics.Texture
import com.lib.gdx.core.AshleyGame

class MainGame : AshleyGame() {

    override fun create() {
        assetManager.load("badlogic.jpg", Texture::class.java)
        assetManager.finishLoading()
        push(Screen1(this))
    }
}


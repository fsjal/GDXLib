package com.lib.gdx.core

import com.badlogic.gdx.ApplicationListener
import com.badlogic.gdx.Gdx

abstract class Game (disposablesManager: DisposablesManager = SimpleDisposablesManager()) :
        ApplicationListener,
        DisposablesManager by disposablesManager,
        ScreensManager by SimpleScreensManager(disposablesManager),
        AssetsManager by SimpleAssetsManager(disposablesManager) {

    override fun render() = current.render(Gdx.graphics.deltaTime)

    override fun pause() = current.pause()

    override fun resume() = current.resume()

    override fun resize(width: Int, height: Int) = current.resize(width, height)
}
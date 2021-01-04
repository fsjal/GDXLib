package com.lib.gdx.core

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader

class SimpleAssetsManager(disposablesManager: DisposablesManager) :
        AssetsManager,
        DisposablesManager by disposablesManager {

    override val assetManager = AssetManager()

    init {
        println("assets dispose add")
        disposables += this
        assetManager.setLoader(FreeTypeFontGenerator::class.java, FreeTypeFontGeneratorLoader(assetManager.fileHandleResolver))
    }

    override fun updateLoading() = sequence { while (!assetManager.update()) yield(assetManager.progress) }

    override fun finishLoading() = assetManager.finishLoading()

    override fun dispose() {
        println("Manageable dispose")
        assetManager.dispose()
    }
}
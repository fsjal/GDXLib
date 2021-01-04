package com.lib.gdx.core

import com.badlogic.gdx.assets.AssetManager

interface AssetsManager {

    val assetManager: AssetManager

    fun updateLoading(): Sequence<Float>

    fun finishLoading()

}
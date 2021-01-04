package com.lib.gdx.util

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable

val TextureRegion.drawable get() = TextureRegionDrawable(this)

operator fun TextureAtlas.get(asset: String): TextureAtlas.AtlasRegion = findRegion(asset)

inline fun Batch.use(block: Batch.() -> Unit) {
    begin()
    block()
    end()
}
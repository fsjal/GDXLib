package com.lib.gdx

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL30
import com.badlogic.gdx.graphics.Texture
import com.lib.gdx.ashley.component
import com.lib.gdx.ashley.entity
import com.lib.gdx.ashley.get
import com.lib.gdx.core.*

class Screen1(private val game: AshleyGame) :
        Screen(),
        ScreensManager by game,
        AssetsManager by game,
        AshleyManager by game {

    override fun create() {
        engine.run {
            entity { component<TextureComponent>(engine) { texture = assetManager["badlogic.jpg"] }}
            systems(RenderSystem(game))
        }
        println(engine.entities.size())
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT)
        engine.update(delta)
    }
}
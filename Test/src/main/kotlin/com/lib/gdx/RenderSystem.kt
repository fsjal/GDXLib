package com.lib.gdx

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.IteratingSystem
import com.lib.gdx.ashley.Family
import com.lib.gdx.ashley.map
import com.lib.gdx.core.Game
import com.lib.gdx.core.ScreensManager

class RenderSystem(game: Game) :
        IteratingSystem(Family[TextureComponent::class]),
        ScreensManager by game {

    private val textureMapper = map<TextureComponent>()

    override fun processEntity(entity: Entity, deltaTime: Float) {
        println("ok")
        val texture = textureMapper[entity].texture
        batch.begin()
        batch.draw(texture, 0f, 0f)
        batch.end()
    }
}
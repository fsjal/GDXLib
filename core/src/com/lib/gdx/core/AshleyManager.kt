package com.lib.gdx.core

import com.badlogic.ashley.core.*
import com.badlogic.ashley.utils.ImmutableArray
import kotlin.reflect.KClass

interface AshleyManager {

    val engine: PooledEngine

    /**
     * Adding entities and systems to the engine
     * */
    fun build(init: AshleyManager.() -> Unit)

    /**
     * Cascade adding systems to the engine
     * */
    fun systems(vararg systems: EntitySystem)

    fun clearEngine()

    /**
     * Update the engine each frame
     * */
    fun updateEngine(delta: Float)

    operator fun invoke(): Engine

    operator fun get(family: Family): ImmutableArray<Entity>

    operator fun get(vararg classes: KClass<out Component>): ImmutableArray<Entity>

    operator fun get(classes: List<KClass<out Component>>): ImmutableArray<Entity>

    operator fun <T: EntitySystem> get(classes: KClass<T>): T
}
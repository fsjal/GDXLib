package com.lib.gdx.ashley

import com.badlogic.ashley.core.Component
import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.PooledEngine

inline fun <reified T: Component> Entity.component(engine: PooledEngine, init: T.() -> Unit): T =
        engine.createComponent(T::class.java).apply(init).also { add(it) }
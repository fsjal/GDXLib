package com.lib.gdx.ashley

import com.badlogic.ashley.core.Component
import com.badlogic.ashley.core.ComponentMapper

inline fun <reified T : Component> map(): ComponentMapper<T> = ComponentMapper.getFor(T::class.java)
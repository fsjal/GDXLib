package com.lib.gdx.ashley

import com.badlogic.ashley.core.*
import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.utils.ImmutableArray
import kotlin.reflect.KClass

operator fun Engine.get(family: Family): ImmutableArray<Entity> = getEntitiesFor(family)

operator fun Engine.get(vararg classes: KClass<out Component>): ImmutableArray<Entity> = get(Family.all(*classes.map { it.java }.toTypedArray()).get())

operator fun Engine.get(classes: List<KClass<out Component>>): ImmutableArray<Entity> = get(Family.one(*classes.map { it.java }.toTypedArray()).get())

operator fun <T: EntitySystem> Engine.get(classes: KClass<T>): T = getSystem(classes.java)

fun PooledEngine.entity(init: Entity.() -> Unit): Entity = createEntity().apply(init).also { addEntity(it) }

inline fun <reified T: Component> PooledEngine.component(init: T.() -> Unit): T = createComponent(T::class.java).apply(init)
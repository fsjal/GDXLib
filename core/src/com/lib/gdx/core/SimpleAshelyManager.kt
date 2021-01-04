package com.lib.gdx.core

import com.badlogic.ashley.core.*
import com.badlogic.ashley.utils.ImmutableArray
import com.badlogic.gdx.utils.Disposable
import kotlin.reflect.KClass

class SimpleAshelyManager(disposablesManager: DisposablesManager, override val engine: PooledEngine) :
        AshleyManager,
        DisposablesManager by disposablesManager {

    init { disposables += this }

    override fun build(init: AshleyManager.() -> Unit) = init()

    override fun systems(vararg systems: EntitySystem) = systems.forEach { engine.addSystem(it) }

    override fun updateEngine(delta: Float) = engine.update(delta)

    override fun clearEngine() = dispose()

    override fun dispose() {
        println("dispose engine")
        engine.systems.asSequence().filter { it is Disposable }.map { it as Disposable }.forEach { it.dispose() }
        engine.removeAllEntities()
        engine.clearPools()
    }

    override operator fun invoke() = engine

    override operator fun get(family: Family): ImmutableArray<Entity> = engine.getEntitiesFor(family)

    override operator fun get(vararg classes: KClass<out Component>): ImmutableArray<Entity> =
            get(Family.all(*classes.map { it.java }.toTypedArray()).get())

    override operator fun get(classes: List<KClass<out Component>>): ImmutableArray<Entity> =
            get(Family.one(*classes.map { it.java }.toTypedArray()).get())

    override operator fun <T: EntitySystem> get(classes: KClass<T>): T = engine.getSystem(classes.java)
}
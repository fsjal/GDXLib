package com.lib.gdx.core

import com.badlogic.ashley.core.PooledEngine

abstract class AshleyGame(engine: PooledEngine = PooledEngine(), disposablesManager: DisposablesManager = SimpleDisposablesManager()) :
        Game(disposablesManager),
        AshleyManager by SimpleAshelyManager(disposablesManager, engine) {
}
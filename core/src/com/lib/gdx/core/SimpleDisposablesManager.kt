package com.lib.gdx.core

import com.badlogic.gdx.utils.Disposable

class SimpleDisposablesManager : DisposablesManager {

    override val disposables = mutableListOf<Disposable>()

    override fun dispose() {
        println("Disposing")
        disposables.forEach { it.dispose() }
    }
}
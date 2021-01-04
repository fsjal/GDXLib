package com.lib.gdx.core

import com.badlogic.gdx.utils.Disposable

interface DisposablesManager : Disposable {

    val disposables: MutableList<Disposable>
}
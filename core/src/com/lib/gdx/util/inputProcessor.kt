package com.lib.gdx.util

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputMultiplexer
import com.badlogic.gdx.InputProcessor

operator fun <T: InputProcessor> T.plus(input: T) = InputMultiplexer(this, input)

operator fun <T: InputProcessor> T.plusAssign(input: T) { Gdx.input.inputProcessor = this + input }
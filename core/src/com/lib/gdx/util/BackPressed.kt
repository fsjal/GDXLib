package com.lib.gdx.util

import com.badlogic.gdx.Input
import com.badlogic.gdx.InputAdapter
import com.lib.gdx.core.ScreensManager

class BackPressed(manager: ScreensManager) : InputAdapter(), ScreensManager by manager {

    override fun keyDown(keycode: Int) =  if (keycode == Input.Keys.BACK) {
        pop()
        true
    } else {
        false
    }
}
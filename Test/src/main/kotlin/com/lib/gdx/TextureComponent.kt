package com.lib.gdx

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.utils.Pool.Poolable

class TextureComponent : Component {

    lateinit var texture: Texture

}
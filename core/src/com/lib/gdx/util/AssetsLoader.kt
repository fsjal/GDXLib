package com.lib.gdx.util

import com.badlogic.gdx.assets.loaders.MusicLoader.MusicParameter
import com.badlogic.gdx.assets.loaders.SkinLoader.SkinParameter
import com.badlogic.gdx.assets.loaders.SoundLoader.SoundParameter
import com.badlogic.gdx.assets.loaders.TextureLoader.TextureParameter
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.lib.gdx.core.AssetsManager

class AssetsLoader(manager: AssetsManager) : AssetsManager by manager {

    fun texture(texturePath: String, params: TextureParameter? = null) = assetManager.load(texturePath, Texture::class.java, params)

    fun music(musicPath: String, params: MusicParameter? = null) = assetManager.load(musicPath, Music::class.java, params)

    fun sound(soundPath: String, params: SoundParameter? = null) = assetManager.load(soundPath, Sound::class.java, params)

    fun skin(skinPath: String, params: SkinParameter? = null) = assetManager.load(skinPath, Skin::class.java, params)

    fun textures(vararg paths: String) = paths.forEach { assetManager.load(it, Texture::class.java) }

    //fun textures2(init: String.() -> Unit) = println(String().apply(init))
}
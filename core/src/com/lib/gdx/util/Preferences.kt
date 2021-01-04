package com.lib.gdx.util

import com.badlogic.gdx.Gdx
import kotlin.reflect.KProperty

class Preferences(@PublishedApi internal val name: String) {

    fun clear() = Gdx.app.getPreferences(name).clear()

    operator fun<T> get(key: String) = Preference<T>(name, key)

    class Preference<T>(@PublishedApi internal val name: String, @PublishedApi internal val key: String){

        var value: T? = null

        inline operator fun<R, reified T> getValue(thisRef: R, property: KProperty<*>) =
                Gdx.app.getPreferences(name).run {
                    when(T::class) {
                        Int::class -> getInteger(key) as T
                        Float::class -> getFloat(key) as T
                        Long::class -> getLong(key) as T
                        Boolean::class -> getBoolean(key) as T
                        else -> getString(key) as T
                    }
                }

        inline operator fun<R, reified T> setValue(thisRef: R, property: KProperty<*>, value: T) =
                with(Gdx.app.getPreferences(name)) {
                    when(T::class) {
                        Int::class -> putInteger(key, value as Int)
                        Float::class -> putFloat(key, value as Float)
                        Long::class -> putLong(key, value as Long)
                        Boolean::class -> putBoolean(key, value as Boolean)
                        else -> putString(key, value as String)
                    }.flush()
                }

        fun remove() = Gdx.app.getPreferences(name).remove(key)
    }
}


package com.lib.gdx.util

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.math.Vector3
import com.lib.gdx.util.r
import com.lib.gdx.util.v

operator fun Vector2.component1() = x

operator fun Vector2.component2() = y

operator fun Vector3.component1() = x

operator fun Vector3.component2() = y

operator fun Vector3.component3() = z

val Vector2.v: Vector2 get() = Vector2(x.v, y.v)

val Vector3.v: Vector3 get() = Vector3(x.v, y.v, z.v)

val Vector2.r: Vector2 get() = Vector2(x.r, y.r)

val Vector3.r: Vector3 get() = Vector3(x.r, y.r, z.r)
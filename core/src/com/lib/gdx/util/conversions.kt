package com.lib.gdx.util

const val P2M = 0.033f
const val M2P = 1 / P2M

// Convert to the virtual value

val Int.v: Float get() = this * P2M

val Float.v: Float get() = this * P2M

val Double.v: Double get() = this * P2M

val Int.virtual: Float get() = this * P2M

val Float.virtual: Float get() = this * P2M

val Double.virtual: Double get() = this * P2M

// Convert to the real value

val Int.r: Float get() = this * M2P

val Float.r: Float get() = this * M2P

val Double.r: Double get() = this * M2P

val Int.real: Float get() = this * M2P

val Float.real: Float get() = this * M2P

val Double.real: Double get() = this * M2P

// Integer

val Int.f: Float get() = this.toFloat()

val Int.d: Double get() = this.toDouble()

// Float

val Float.i: Int get() = this.toInt()

val Float.d: Double get() = this.toDouble()

// Double

val Double.i: Int get() = this.toInt()

val Double.f: Float get() = this.toFloat()
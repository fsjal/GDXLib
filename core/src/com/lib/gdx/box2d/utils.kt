package com.lib.gdx.box2d

import com.badlogic.gdx.physics.box2d.*

operator fun Body.get(data: Any) = fixtureList.find { it.userData == data }

operator fun Body.get(position: Int): Fixture? = fixtureList[position]
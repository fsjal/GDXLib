package com.lib.gdx.box2d.collision

import com.badlogic.gdx.physics.box2d.Contact
import com.badlogic.gdx.physics.box2d.ContactImpulse
import com.badlogic.gdx.physics.box2d.Fixture
import com.badlogic.gdx.physics.box2d.Manifold

open class CollisionAdapter : CollisionListener {

    override fun beginContact(collisionFixture: Fixture, contact: Contact) {

    }

    override fun endContact(collisionFixture: Fixture, contact: Contact) {

    }

    override fun preSolve(collisionFixture: Fixture, contact: Contact, oldManifold: Manifold) {

    }

    override fun postSolve(collisionFixture: Fixture, contact: Contact, impulse: ContactImpulse) {

    }
}
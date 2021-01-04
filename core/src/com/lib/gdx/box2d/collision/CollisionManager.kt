package com.lib.gdx.box2d.collision

import com.badlogic.gdx.physics.box2d.Contact
import com.badlogic.gdx.physics.box2d.ContactImpulse
import com.badlogic.gdx.physics.box2d.ContactListener
import com.badlogic.gdx.physics.box2d.Manifold

/**
 * Utility class for Box2D collisions
 */
class CollisionManager : ContactListener {

    private val callbacks = mutableMapOf<Any, CollisionListener>()

    override fun beginContact(contact: Contact) {
        callbacks[contact.fixtureA.userData]?.beginContact(contact.fixtureB, contact)
        callbacks[contact.fixtureB.userData]?.beginContact(contact.fixtureA, contact)
    }

    override fun endContact(contact: Contact) {
        callbacks[contact.fixtureA.userData]?.endContact(contact.fixtureB, contact)
        callbacks[contact.fixtureB.userData]?.endContact(contact.fixtureA, contact)
    }

    override fun preSolve(contact: Contact, oldManifold: Manifold) {
        callbacks[contact.fixtureA.userData]?.preSolve(contact.fixtureB, contact, oldManifold)
        callbacks[contact.fixtureB.userData]?.preSolve(contact.fixtureA, contact, oldManifold)
    }

    override fun postSolve(contact: Contact, impulse: ContactImpulse) {
        callbacks[contact.fixtureA.userData]?.postSolve(contact.fixtureB, contact, impulse)
        callbacks[contact.fixtureB.userData]?.postSolve(contact.fixtureA, contact, impulse)
    }

    operator fun invoke() = callbacks

    operator fun get(data: Any) = callbacks[data]

    /**
     * Define a callback for a collision
     * If a fixture with UserData equals to data then the callback of that fixture is called when collision involving this fixture
     * @param data Must be the same as in userData
     * @param callback Called when collision
     */
    operator fun set(data: Any, callback: CollisionListener) {
        callbacks[data] = callback
    }

    /**
     * Remove a specific collision callback from the manager
     * @param data
     */
    fun remove(data: Any) = callbacks.remove(data)

    /**
     * Clear all collision callbacks from the manager
     */
    fun dispose() = callbacks.clear()
}
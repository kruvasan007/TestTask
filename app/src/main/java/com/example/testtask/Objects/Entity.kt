package com.example.testtask.Objects

import android.widget.Toast

open class Entity(var attack: Int = 0, var defence: Int = 0, var health: Int = 0) {
    var liveState: Boolean = true
    protected val maxHealth: Int = health
    private lateinit var damage: Array<Int>;

    fun setDamage(start: Int, end: Int) {
        if (start < 0 || end < 0 || end < start) println("incorrect input")
        else damage = Array(end - start) { i -> (start + i) }
    }

    fun correctHealth() {
        if (health > maxHealth)
            health = maxHealth
    }

    fun takeDamage(damage: Int): Boolean {
        if(damage < 0) {
            println("incorrect input")
            return true
        }
        return if (liveState) {
            this.health -= damage
            if (health <= 0) {
                health = 0
                liveState = false
            }
            false
        } else true
    }
}
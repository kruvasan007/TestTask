package com.example.testtask.Objects

class Gamer(attack: Int, defence: Int, health: Int) : Entity(attack, defence, health) {
    private var regenerationCount: Int = 0

    fun regeneration() {
        if (regenerationCount < 3) {
            this.health += this.maxHealth / 2
            correctHealth()
            regenerationCount++
        }
    }
}
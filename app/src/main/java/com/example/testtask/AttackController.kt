package com.example.testtask

import com.example.testtask.Objects.Entity
import kotlin.random.Random

class AttackController {
    private fun calculateModificator(attacking: Entity, defending: Entity) =
        attacking.attack - defending.defence + 1

    fun calculateSuccess(attacking: Entity, defending: Entity): Boolean {
        var modificatior = calculateModificator(attacking, defending)
        println(attacking.attack)
        if (modificatior < 1) modificatior = 1
        var flag = false
        for (i in modificatior downTo 1) {
            val generateNumber = Random.nextInt(1, 6)
            if (generateNumber == 5 || generateNumber == 6)
                flag = true
        }
        if (flag)
            if (defending.takeDamage(attacking.attack)) return true
        return false
    }
}
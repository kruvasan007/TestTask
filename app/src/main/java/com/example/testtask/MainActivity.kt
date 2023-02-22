package com.example.testtask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.SeekBar
import android.widget.Toast
import com.example.testtask.Objects.Gamer
import com.example.testtask.Objects.Monster
import com.example.testtask.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val n: Int = 100
    private val gamer = Gamer(4, 3, n)
    private val monster = Monster(3, 5, n)
    private val attackController: AttackController = AttackController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initGamer()
        initMonster()

        binding.attackButton.setOnClickListener {
            if(gamer.liveState) {
                if (attackController.calculateSuccess(gamer, monster))
                    Toast.makeText(this, "Вы убили противника!", Toast.LENGTH_SHORT).show()
                binding.healthMonster.text = "Здоровье: " + monster.health.toString()
                binding.health.text = "Здоровье: " + gamer.health.toString()
            } else
                Toast.makeText(this, "Вы не можете атаковать, вы мертвы", Toast.LENGTH_SHORT).show()
        }

        binding.simulationButton.setOnClickListener { startTimer() }


        binding.defenceBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {}
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                val value = seekBar?.progress.toString()
                gamer.defence = value.toInt()
                binding.defence.text = "Защита: " + value
            }

        })

        binding.attackBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {}
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                val value = seekBar?.progress.toString()
                gamer.attack = value.toInt()
                binding.attack.text = "Атака: " + value
            }

        })

        binding.regenerationButton.setOnClickListener {
            gamer.regeneration()
            binding.health.text = "Здоровье: " + gamer.health.toString()
        }

    }

    private fun startTimer() {
        Toast.makeText(applicationContext, "Запущена автоматическая атака", Toast.LENGTH_SHORT).show()
        val timer = object : CountDownTimer(20000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                if (attackController.calculateSuccess(monster, gamer))
                    Toast.makeText(applicationContext, "Вы были убиты!", Toast.LENGTH_SHORT).show()
                binding.healthMonster.text = "Здоровье: " + monster.health.toString()
                binding.health.text = "Здоровье: " + gamer.health.toString()
            }
            override fun onFinish() {}
        }
        timer.start()
    }

    private fun initMonster() {
        monster.setDamage(1, 3)
        binding.attackMonster.text = "Атака: " + monster.attack.toString()
        binding.defenceMonster.text = "Защита: " + monster.defence.toString()
        binding.healthMonster.text = "Здоровье: " + monster.health.toString()
    }

    private fun initGamer() {
        gamer.setDamage(1, 6)
        binding.attack.text = "Атака: " + gamer.attack.toString()
        binding.defence.text = "Защита: " + gamer.defence.toString()
        binding.health.text = "Здоровье: " + gamer.health.toString()
    }
}
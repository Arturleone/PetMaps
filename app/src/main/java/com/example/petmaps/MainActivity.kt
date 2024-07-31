package com.example.petmaps

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)
        val timer: Int
        if (verificarDownload()) timer = 10000 else timer = 2000
            Handler().postDelayed({
                val intent = Intent(this, HomeScreen::class.java)
                startActivity(intent)
                finish()
            }, timer.toLong())

    }

    private fun verificarDownload (): Boolean {
        val sharedPreferences: SharedPreferences = getSharedPreferences("FirstApp", MODE_PRIVATE)
        val firstAppDownload = sharedPreferences.getBoolean("firstAppDownload", true)
        if (firstAppDownload) {
            sharedPreferences.edit().putBoolean("firstAppDownload", false).apply()
            return true
        } else return false

    }
}
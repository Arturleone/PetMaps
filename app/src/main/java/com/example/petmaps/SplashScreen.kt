package com.example.petmaps

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()

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
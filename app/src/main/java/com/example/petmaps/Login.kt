package com.example.petmaps

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val botao: Button = findViewById(R.id.loginpet)
        botao.setOnClickListener {
            val intent = Intent(this, Cadastro::class.java)
            startActivity(intent)
        }
    }
}

package com.example.petmaps

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val botao: Button = findViewById(R.id.loginpet)
        val cadastrar = findViewById<TextView>(R.id.cadastrar)
        val loginRedes = findViewById<LinearLayout>(R.id.loginredes)

        loginRedes.setOnClickListener{
            Toast.makeText(this, "Função Desabilitada", Toast.LENGTH_SHORT).show()
        }
        cadastrar.setOnClickListener{
            startActivity(Intent(this, Cadastro::class.java))
            finish()
        }
        botao.setOnClickListener {
            val intent = Intent(this, Cadastro::class.java)
            startActivity(intent)
            finish()
        }

    }
}

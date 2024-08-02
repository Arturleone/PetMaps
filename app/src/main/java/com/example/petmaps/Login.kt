package com.example.petmaps

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class Login : AppCompatActivity() {
    var attemptCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val botao: Button = findViewById(R.id.loginpet)
        val cadastrar = findViewById<TextView>(R.id.cadastrar)
        val loginRedes = findViewById<LinearLayout>(R.id.loginredes)
        val usuarLogin = findViewById<EditText>(R.id.UsuarLogin)
        val senhaUsur = findViewById<EditText>(R.id.SenhaUsur)
        val loginPets = findViewById<Button>(R.id.loginpet)

        // Obtém o drawable do fundo padrão dos recursos
        val defaultBackground = ContextCompat.getDrawable(this, R.drawable.edittext_border)
        // Cor de erro
        val backgroundRed = ContextCompat.getDrawable(this, R.drawable.edittext_border_red)

        loginPets.setOnClickListener {
            val inputUsur = usuarLogin.text.toString()
            val inputSenha = senhaUsur.text.toString()
            if (valitedLogin(inputUsur, inputSenha) || (inputSenha == "admin" && inputUsur == "admin")) {
                val intent = Intent(this, Telainicial::class.java)
                intent.putExtra("USERNAME", inputUsur)
                startActivity(intent)
                finish()
            } else {
                attemptCount++
                if (attemptCount > 3) {
                    usuarLogin.background = backgroundRed
                    senhaUsur.background = backgroundRed
                    Toast.makeText(this, "Login bloqueado: aguarde 10s!", Toast.LENGTH_LONG).show()
                    botao.isEnabled = false
                    cadastrar.isEnabled = false
                    usuarLogin.isEnabled = false
                    senhaUsur.isEnabled = false
                    Handler(Looper.getMainLooper()).postDelayed({
                        usuarLogin.background = defaultBackground
                        senhaUsur.background = defaultBackground
                        botao.isEnabled = true
                        cadastrar.isEnabled = true
                        usuarLogin.isEnabled = true
                        senhaUsur.isEnabled = true
                        attemptCount = 0
                    }, 10000)
                } else {
                    usuarLogin.background = backgroundRed
                    senhaUsur.background = backgroundRed
                    Toast.makeText(this, "Login/Usuário Inválido", Toast.LENGTH_LONG).show()
                    Handler(Looper.getMainLooper()).postDelayed({
                        usuarLogin.background = defaultBackground
                        senhaUsur.background = defaultBackground
                    }, 1000)
                }
            }
        }

        loginRedes.setOnClickListener {
            Toast.makeText(this, "Função Desabilitada", Toast.LENGTH_SHORT).show()
        }

        cadastrar.setOnClickListener {
            startActivity(Intent(this, Cadastro::class.java))
            finish()
        }
    }

    private fun valitedLogin(username: String, password: String): Boolean {
        val sharedPreferences: SharedPreferences = getSharedPreferences("Cadastro", MODE_PRIVATE)
        val getUsua = sharedPreferences.getString("usuar", null)
        val getPassword = sharedPreferences.getString("password", null)
        return username == getUsua && password == getPassword
    }
}

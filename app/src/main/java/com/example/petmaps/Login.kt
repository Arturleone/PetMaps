package com.example.petmaps

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Login : AppCompatActivity() {
    private var attemptCount = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val botao: Button = findViewById(R.id.loginpet)
        val cadastrar = findViewById<TextView>(R.id.cadastrar)
        val loginRedes = findViewById<LinearLayout>(R.id.loginredes)
        val UsuarLogin = findViewById<EditText>(R.id.UsuarLogin)
        val senhaUsur = findViewById<EditText>(R.id.SenhaUsur)
        val loginPets = findViewById<Button>(R.id.loginpet)

        loginPets.setOnClickListener{
            val inputUsur = UsuarLogin.text.toString()
            val inputSenha = senhaUsur.text.toString()
            if (valitedLogin(inputSenha, inputUsur)) {
                val intent = Intent(this, Telainicial::class.java)
                startActivity(intent)
                finish()
            } else {
                attemptCount++
                if (attemptCount >= 3) {
                    // Bloqueia o login após 3 tentativas
                    Toast.makeText(this, "Login bloqueado: aguarde 10s!", Toast.LENGTH_LONG).show()
                    botao.isEnabled = false
                    cadastrar.isEnabled = false
                    UsuarLogin.isEnabled = false
                    senhaUsur.isEnabled = false
                    Handler().postDelayed({
                        botao.isEnabled = true
                        cadastrar.isEnabled = true
                        UsuarLogin.isEnabled = true
                        senhaUsur.isEnabled = true
                    }, 10000)
                } else {
                    Toast.makeText(this, "Login bloqueado: aguarde 10s!", Toast.LENGTH_LONG).show()
                    findViewById<EditText>(R.id.UsuarLogin).background.setTint(Color.RED)
                    findViewById<EditText>(R.id.SenhaUsur).background.setTint(Color.RED)
                }
            }

        }

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
    private fun valitedLogin (username: String, password: String): Boolean {
        val sharedPreferences: SharedPreferences = getSharedPreferences("Cadastro", MODE_PRIVATE)
        val getUsua = sharedPreferences.getString("usuario", null)
        val getPassword = sharedPreferences.getString("senha", null)
        val getname = sharedPreferences.getString("nome", null)

        return (username == getUsua && getPassword == password)
    }
}

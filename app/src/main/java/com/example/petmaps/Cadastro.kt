package com.example.petmaps

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EdgeEffect
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.regex.Pattern

class Cadastro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cadastro)

        val possuoCadastro = findViewById<TextView>(R.id.possuocadastro)
        val nome = findViewById<EditText>(R.id.Nome)
        val numero = findViewById<EditText>(R.id.Numero)
        val nomeUsur = findViewById<EditText>(R.id.NomeUsur)
        val senha = findViewById<EditText>(R.id.SenhaCadastro)
        val confirmSenha = findViewById<EditText>(R.id.ConfirmSenha)
        val email = findViewById<EditText>(R.id.Email)
        val cadastroPet = findViewById<Button>(R.id.cadastroPet)
        val voltar = findViewById<ImageView>(R.id.Voltar)
        possuoCadastro.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            finish()
        }
        voltar.setOnClickListener{
            startActivity(Intent(this, Login::class.java))
            finish()
        }
        cadastroPet.setOnClickListener {
            val inputEmail = email.text.toString()
            val inputNome = nome.text.toString()
            val inputNumero = numero.text.toString()
            val inputSenha = senha.text.toString()
            val inputConfirmSenha = confirmSenha.text.toString()
            val inputUsuarCadastro = nomeUsur.text.toString()

            if (inputEmail.isBlank() || inputUsuarCadastro.isBlank() || inputSenha.isBlank() || inputNome.isBlank() || inputNumero.isBlank() || inputConfirmSenha.isBlank()) {
                errorShowMensage("Todos os campos são obrigatórios")
            } else if (verificarNumber(inputNumero)) {
                numero.error = "Número Incorreto"
            } else if(!verificarSenha(inputSenha)) {
                senha.error = "A senha deve ter no mínimo 8 caracteres, conter pelo menos uma letra maiúscula e um caractere especial."
            } else if(inputSenha != inputConfirmSenha) {
                senha.error = "Senhas Incoerentes"
                confirmSenha.error = "Senhas Incoerentes"
            } else if (!verificarEmail(inputEmail)) {
                email.error = "Email Incorreto"
            } else {
                Toast.makeText(this, "Cadastro Conluído!!", Toast.LENGTH_SHORT).show()
                adicionarUsua(inputUsuarCadastro, inputSenha)
                val intent = Intent(this, Login::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun verificarSenha(password: String): Boolean {
        val newPassword = password
        if (newPassword.length < 8) return false
        if (!newPassword.any { !it.isLetterOrDigit() }) return false
        if (!newPassword.any { it.isUpperCase() }) return false
        return true
    }

    private fun errorShowMensage(mensagem: String) {
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show()
    }
    private fun verificarEmail (email: String): Boolean {
        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) return true else return false
    }
    private fun verificarNumber(numero: String): Boolean {
        if(Patterns.PHONE.matcher(numero).matches()) return true else return false
    }

    private fun adicionarUsua(usuario: String, senha: String) {
        val sharedPreferences: SharedPreferences = getSharedPreferences("", MODE_PRIVATE)
        val Usuarios = sharedPreferences.edit()
        Usuarios.putString("usuario", usuario)
        Usuarios.putString("senha", senha)
        Usuarios.apply()
    }
}


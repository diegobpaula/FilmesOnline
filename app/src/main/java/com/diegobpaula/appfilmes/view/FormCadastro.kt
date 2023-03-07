package com.diegobpaula.appfilmes.view

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.diegobpaula.appfilmes.R
import com.diegobpaula.appfilmes.databinding.ActivityFormCadastroBinding
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.ktx.Firebase

class FormCadastro : AppCompatActivity() {

    private lateinit var binding: ActivityFormCadastroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormCadastroBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar!!.hide()
        window.statusBarColor = Color.parseColor("#FFFFFF")
        binding.editEmail.requestFocus()

        Firebase

        binding.btVamosLa.setOnClickListener {
            val email = binding.editEmail.text.toString()

            if (!email.isEmpty()) {
                binding.containerSenha.visibility = View.VISIBLE
                binding.btVamosLa.visibility = View.GONE
                binding.btContinuar.visibility = View.VISIBLE
                binding.txtTitulo.setText("Um mundo de séries e filmes\n ilimitados espera por você.")
                binding.txtDescricao.setText("Crie uma conta para saber mais sobre\n o nosso App de Filmes.")
                binding.containerEmail.boxStrokeColor = Color.parseColor("#FF018786")
                binding.containerEmail.helperText = ""
                binding.containerHeader.visibility = View.VISIBLE

            } else {
                binding.containerEmail.boxStrokeColor = Color.parseColor("#FF0000")
                binding.containerEmail.helperText = "O e-mail é obrigatório"
            }
        }

        binding.btContinuar.setOnClickListener {
            val email = binding.editEmail.text.toString()
            val senha = binding.editSenha.text.toString()

            if (!email.isEmpty() && !senha.isEmpty()) {
                cadastro(email, senha)

            } else if (senha.isEmpty()) {
                binding.containerSenha.boxStrokeColor = Color.parseColor("#FF0000")
                binding.containerSenha.helperText = "A senha é obrigatória."
                binding.containerEmail.boxStrokeColor = Color.parseColor("#FF018786")
            } else if (email.isEmpty()) {
                binding.containerEmail.boxStrokeColor = Color.parseColor("#FF0000")
                binding.containerEmail.helperText = "O e-mail é obrigatório"
            }
        }

        binding.txtEntrar.setOnClickListener {
            val intent = Intent(this, FormLogin::class.java)
            startActivity(intent)
        }

    }

    private fun cadastro(email: String, senha: String) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha)
            .addOnCompleteListener { cadastro ->
                if (cadastro.isSuccessful) {
                    Toast.makeText(this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT)
                        .show()
                    binding.containerEmail.helperText = ""
                    binding.containerSenha.helperText = ""
                    binding.containerEmail.boxStrokeColor = Color.parseColor("#FF018786")
                    binding.containerSenha.boxStrokeColor = Color.parseColor("#FF018786")
                    val intent = Intent(this, FormLogin::class.java)
                    startActivity(intent)
                    finish()
                }
            }.addOnFailureListener {

            val erro = it

            when {
                erro is FirebaseAuthWeakPasswordException -> {
                    binding.containerSenha.helperText =
                        "Digite uma senha com no mínimo 6 caracteres!"
                    binding.containerSenha.boxStrokeColor = Color.parseColor("#FF0000")
                }
                erro is FirebaseAuthUserCollisionException -> {
                    binding.containerEmail.helperText = "Esta conta já foi cadastrada!"
                    binding.containerEmail.boxStrokeColor = Color.parseColor("#FF0000")
                }
                erro is FirebaseNetworkException -> {
                    binding.containerSenha.helperText = "Sem conexão com a internet!"
                    binding.containerEmail.boxStrokeColor = Color.parseColor("#FF0000")
                }
                else -> {
                    Toast.makeText(this, "Erro ao cadastrar usuário!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
package com.diegobpaula.appfilmes.view

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.diegobpaula.appfilmes.R
import com.diegobpaula.appfilmes.adapter.AdapterCategoria
import com.diegobpaula.appfilmes.databinding.ActivityFormCadastroBinding
import com.diegobpaula.appfilmes.databinding.ActivityTelaPrincipalBinding
import com.diegobpaula.appfilmes.model.Categoria
import com.google.firebase.auth.FirebaseAuth

class TelaPrincipal : AppCompatActivity() {

    private lateinit var binding: ActivityTelaPrincipalBinding
    private lateinit var adapterCategoria: AdapterCategoria
    private val listaCategorias: MutableList<Categoria> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTelaPrincipalBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar!!.hide()
        window.statusBarColor = Color.parseColor("#000000")

        val recyclerViewFilmes = binding.recyclerViewFilmes
        recyclerViewFilmes.layoutManager = LinearLayoutManager(this)
        recyclerViewFilmes.setHasFixedSize(true)
        adapterCategoria = AdapterCategoria(this, listaCategorias)
        recyclerViewFilmes.adapter = adapterCategoria
        getCategorias()






        binding.txtSair.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, FormLogin::class.java)
            startActivity(intent)
            finish()
            Toast.makeText(this, "Usuário deslogado com sucesso.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getCategorias(){
        val categoria1 = Categoria("Terror")
        listaCategorias.add(categoria1)
        val categoria2 = Categoria("Anime")
        listaCategorias.add(categoria2)
        val categoria3 = Categoria("Comédia")
        listaCategorias.add(categoria3)
        val categoria4 = Categoria("Suspense")
        listaCategorias.add(categoria4)

    }
}
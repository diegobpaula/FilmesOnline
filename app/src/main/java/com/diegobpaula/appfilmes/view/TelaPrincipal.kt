package com.diegobpaula.appfilmes.view

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.diegobpaula.appfilmes.adapter.AdapterCategoria
import com.diegobpaula.appfilmes.databinding.ActivityTelaPrincipalBinding
import com.diegobpaula.appfilmes.model.Categoria
import com.diegobpaula.appfilmes.model.Categorias
import com.google.android.gms.common.api.Api
import com.google.firebase.auth.FirebaseAuth
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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

        binding.txtSair.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, FormLogin::class.java)
            startActivity(intent)
            finish()
            Toast.makeText(this, "Usuário deslogado com sucesso.", Toast.LENGTH_SHORT).show()
        }

        // Configuração retrofit
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://stackmobile.com.br/")
            .build()
            .create(com.diegobpaula.appfilmes.api.Api::class.java)

        retrofit.listaCategorias().enqueue(object : Callback<Categorias>{
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<Categorias>, response: Response<Categorias>) {
                if (response.code() == 200)
                    response.body()?.let {
                        adapterCategoria.listaCategorias.addAll(it.categorias)
                        adapterCategoria.notifyDataSetChanged()
                        binding.containerProgressBar.visibility = View.GONE
                        binding.progressBar.visibility = View.GONE
                        binding.txtCarregando.visibility = View.GONE
                    }
            }

            override fun onFailure(call: Call<Categorias>, t: Throwable) {
                Toast.makeText(applicationContext,"Erro ao buscar todos os filmes!",Toast.LENGTH_SHORT).show()
            }

        })

    }
}
package com.diegobpaula.appfilmes.view

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.diegobpaula.appfilmes.R
import com.diegobpaula.appfilmes.databinding.ActivityDetalhesFilmeBinding

class DetalhesFilme : AppCompatActivity() {

    private lateinit var binding: ActivityDetalhesFilmeBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalhesFilmeBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)
        supportActionBar!!.hide()
        window.statusBarColor = Color.parseColor("#000000")

        val capa = intent.extras?.getString("capa")
        val nome = intent.extras?.getString("nome")
        val descricao = intent.extras?.getString("descricao")
        val elenco = intent.extras?.getString("elenco")

        Glide.with(this).load(capa).centerCrop().into(binding.capaFilme)
        binding.txtNome.setText(nome).toString()
        binding.txtDescricao.setText("Descrição $descricao").toString()
        binding.txtElenco.setText("Elenco: $elenco").toString()
    }

}
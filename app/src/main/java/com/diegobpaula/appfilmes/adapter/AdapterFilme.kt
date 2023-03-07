package com.diegobpaula.appfilmes.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.diegobpaula.appfilmes.databinding.FilmeItemBinding
import com.diegobpaula.appfilmes.model.Filme
import com.diegobpaula.appfilmes.view.DetalhesFilme

class AdapterFilme(private val context: Context, private val listaFilmes: MutableList<Filme>) :
    RecyclerView.Adapter<AdapterFilme.FilmeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmeViewHolder {
        val itemLista = FilmeItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return FilmeViewHolder(itemLista)
    }

    override fun getItemCount() = listaFilmes.size

    override fun onBindViewHolder(holder: FilmeViewHolder, position: Int) {
        Glide.with(context).load(listaFilmes[position].capa).centerCrop().into(holder.capa)

        holder.capa.setOnClickListener {
            val intent = Intent(context, DetalhesFilme::class.java)
            intent.putExtra("capa", listaFilmes[position].capa)
            intent.putExtra("nome", listaFilmes[position].nome)
            intent.putExtra("descricao", listaFilmes[position].descricao)
            intent.putExtra("elenco", listaFilmes[position].elenco)
            context.startActivity(intent)

        }
    }

    inner class FilmeViewHolder(binding: FilmeItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val capa = binding.capaFilme
    }

}
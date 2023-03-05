package com.diegobpaula.appfilmes.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.diegobpaula.appfilmes.databinding.FilmeItemBinding
import com.diegobpaula.appfilmes.model.Filme

class AdapterFilme(private val context: Context, private val listaFilmes: MutableList<Filme>) :
    RecyclerView.Adapter<AdapterFilme.FilmeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmeViewHolder {
        val itemLista = FilmeItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return FilmeViewHolder(itemLista)
    }

    override fun getItemCount() = listaFilmes.size

    override fun onBindViewHolder(holder: FilmeViewHolder, position: Int) {
        holder.capa.setImageResource(listaFilmes[position].capa!!)
    }

    inner class FilmeViewHolder(binding: FilmeItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val capa = binding.capaFilme
    }

}
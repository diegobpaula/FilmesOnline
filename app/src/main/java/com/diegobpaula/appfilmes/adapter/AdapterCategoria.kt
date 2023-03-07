package com.diegobpaula.appfilmes.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.diegobpaula.appfilmes.databinding.CategoriaItemBinding
import com.diegobpaula.appfilmes.model.Categoria
import com.diegobpaula.appfilmes.model.Filme

class AdapterCategoria(
    private val context: Context,
    val listaCategorias: MutableList<Categoria>
) :
    RecyclerView.Adapter<AdapterCategoria.CategoriaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriaViewHolder {
        val itemLista = CategoriaItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return CategoriaViewHolder(itemLista)
    }

    override fun getItemCount() = listaCategorias.size

    override fun onBindViewHolder(holder: CategoriaViewHolder, position: Int) {
        holder.titulo.text = listaCategorias[position].titulo

        val categoria = listaCategorias[position]

        holder.recyclerViewFilmes.adapter = AdapterFilme(context, categoria.filmes)
        holder.recyclerViewFilmes.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    inner class CategoriaViewHolder(binding: CategoriaItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val titulo = binding.txtTitulo
        val recyclerViewFilmes = binding.recyclerViewFilmes
    }
}
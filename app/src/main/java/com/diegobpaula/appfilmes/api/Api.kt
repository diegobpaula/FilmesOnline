package com.diegobpaula.appfilmes.api

import com.diegobpaula.appfilmes.model.Categorias
import retrofit2.Call
import retrofit2.http.GET

interface Api {

    @GET("/filmes")
    fun listaCategorias(): Call<Categorias>
}
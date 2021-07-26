package com.kartikcd.myapplication.data.api

import com.kartikcd.myapplication.data.model.pokemondetail.PokemonDetail
import com.kartikcd.myapplication.data.model.pokemonlist.PokemonList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApiService {

    //Pokemon List Call
    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Response<PokemonList>

    //Pokemon Detail call
    @GET("pokemon/{pokemonName}")
    suspend fun getPokemonDetail(
        @Path("pokemonName") pokemonName: String
    ): Response<PokemonDetail>
}
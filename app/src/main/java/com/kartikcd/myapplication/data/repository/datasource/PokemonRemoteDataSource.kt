package com.kartikcd.myapplication.data.repository.datasource

import com.kartikcd.myapplication.data.model.pokemondetail.PokemonDetail
import com.kartikcd.myapplication.data.model.pokemonlist.PokemonList
import retrofit2.Response

interface PokemonRemoteDataSource {

    suspend fun getPokemonList(offset: Int, limit: Int): Response<PokemonList>
    suspend fun getSearchedPokemonList(offset: Int, limit: Int): Response<PokemonList>
    suspend fun getPokemonDetail(pokemonName: String): Response<PokemonDetail>

}
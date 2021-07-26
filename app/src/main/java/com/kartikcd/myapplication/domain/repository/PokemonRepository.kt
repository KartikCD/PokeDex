package com.kartikcd.myapplication.domain.repository

import com.kartikcd.myapplication.data.model.pokemondetail.PokemonDetail
import com.kartikcd.myapplication.data.model.pokemonlist.PokemonList
import com.kartikcd.myapplication.data.util.Resource

interface PokemonRepository {

    suspend fun getPokemonList(offset: Int, limit: Int): Resource<PokemonList>
    suspend fun getSearchedPokemonList(offset: Int, limit: Int): Resource<PokemonList>
    suspend fun getPokemonDetail(pokemonName: String): Resource<PokemonDetail>

}
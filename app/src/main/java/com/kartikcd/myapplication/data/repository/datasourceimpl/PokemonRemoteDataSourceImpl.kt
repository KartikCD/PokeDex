package com.kartikcd.myapplication.data.repository.datasourceimpl

import com.kartikcd.myapplication.data.api.PokemonApiService
import com.kartikcd.myapplication.data.model.pokemondetail.PokemonDetail
import com.kartikcd.myapplication.data.model.pokemonlist.PokemonList
import com.kartikcd.myapplication.data.repository.datasource.PokemonRemoteDataSource
import retrofit2.Response

class PokemonRemoteDataSourceImpl(
    private val pokemonApiService: PokemonApiService
): PokemonRemoteDataSource {

    override suspend fun getPokemonList(offset: Int, limit: Int): Response<PokemonList> {
        return pokemonApiService.getPokemonList(offset, limit)
    }

    override suspend fun getSearchedPokemonList(offset: Int, limit: Int): Response<PokemonList> {
        TODO("Not yet implemented")
    }

    override suspend fun getPokemonDetail(pokemonName: String): Response<PokemonDetail> {
        return pokemonApiService.getPokemonDetail(pokemonName)
    }
}
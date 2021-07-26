package com.kartikcd.myapplication.data.repository

import com.kartikcd.myapplication.data.model.pokemondetail.PokemonDetail
import com.kartikcd.myapplication.data.model.pokemonlist.PokemonList
import com.kartikcd.myapplication.data.repository.datasource.PokemonRemoteDataSource
import com.kartikcd.myapplication.data.util.Resource
import com.kartikcd.myapplication.domain.repository.PokemonRepository
import retrofit2.Response

class PokemonRepositoryImpl(
    private val pokemonRemoteDataSource: PokemonRemoteDataSource
): PokemonRepository {

    override suspend fun getPokemonList(offset: Int, limit: Int): Resource<PokemonList> {
        return responseToResource(pokemonRemoteDataSource.getPokemonList(offset, limit))
    }

    override suspend fun getSearchedPokemonList(offset: Int, limit: Int): Resource<PokemonList> {
        TODO("Not yet implemented")
    }

    override suspend fun getPokemonDetail(pokemonName: String): Resource<PokemonDetail> {
        return responseToResourceWithPokemonDetail(pokemonRemoteDataSource.getPokemonDetail(pokemonName))
    }

    private fun responseToResource(response: Response<PokemonList>):Resource<PokemonList>{
        if(response.code() == 200){
            response.body()?.let {result->
                return Resource.Success(result)
            }
        }
        return Resource.Error("No pokemon found.")
    }

    private fun responseToResourceWithPokemonDetail(response: Response<PokemonDetail>):Resource<PokemonDetail>{
        if(response.code() == 200){
            response.body()?.let {result->
                return Resource.Success(result)
            }
        }
        return Resource.Error("No pokemon found.")
    }
}
package com.kartikcd.myapplication.domain.usecase

import com.kartikcd.myapplication.data.model.pokemondetail.PokemonDetail
import com.kartikcd.myapplication.data.model.pokemonlist.PokemonList
import com.kartikcd.myapplication.data.util.Resource
import com.kartikcd.myapplication.domain.repository.PokemonRepository

class GetPokemonListUseCase(private val pokemonRepository: PokemonRepository) {
    suspend fun execute(pokemonName: String): Resource<PokemonDetail> {
        return pokemonRepository.getPokemonDetail(pokemonName)
    }
}
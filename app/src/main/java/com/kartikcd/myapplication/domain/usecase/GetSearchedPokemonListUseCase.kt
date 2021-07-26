package com.kartikcd.myapplication.domain.usecase

import com.kartikcd.myapplication.data.model.pokemonlist.PokemonList
import com.kartikcd.myapplication.data.util.Resource
import com.kartikcd.myapplication.domain.repository.PokemonRepository

class GetSearchedPokemonListUseCase(private val pokemonRepository: PokemonRepository) {
    suspend fun execute(offset: Int, limit: Int): Resource<PokemonList> {
        return pokemonRepository.getSearchedPokemonList(offset, limit)
    }
}
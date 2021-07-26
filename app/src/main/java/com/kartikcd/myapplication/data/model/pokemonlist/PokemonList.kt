package com.kartikcd.myapplication.data.model.pokemonlist

data class PokemonList(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Result>
)
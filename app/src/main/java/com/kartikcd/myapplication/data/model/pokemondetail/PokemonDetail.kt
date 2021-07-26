package com.kartikcd.myapplication.data.model.pokemondetail

data class PokemonDetail(
    val height: Int,
    val name: String,
    val stats: List<Stat>,
    val types: List<Type>,
    val weight: Int
)
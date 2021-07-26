package com.kartikcd.myapplication.presentation.di

import com.kartikcd.myapplication.domain.repository.PokemonRepository
import com.kartikcd.myapplication.domain.usecase.GetPokemonDetailUseCase
import com.kartikcd.myapplication.domain.usecase.GetPokemonListUseCase
import com.kartikcd.myapplication.domain.usecase.GetSearchedPokemonListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun providesGetPokemonDetailUseCase(
        pokemonRepository: PokemonRepository
    ): GetPokemonDetailUseCase {
        return GetPokemonDetailUseCase(pokemonRepository)
    }

    @Singleton
    @Provides
    fun providesGetPokemonListUseCase(
        pokemonRepository: PokemonRepository
    ): GetPokemonListUseCase {
        return GetPokemonListUseCase(pokemonRepository)
    }

    @Singleton
    @Provides
    fun providesGetSearchedPokemonListUseCase(
        pokemonRepository: PokemonRepository
    ): GetSearchedPokemonListUseCase {
        return GetSearchedPokemonListUseCase(pokemonRepository)
    }

}
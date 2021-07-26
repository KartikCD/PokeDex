package com.kartikcd.myapplication.presentation.di

import com.kartikcd.myapplication.data.repository.PokemonRepositoryImpl
import com.kartikcd.myapplication.data.repository.datasource.PokemonRemoteDataSource
import com.kartikcd.myapplication.domain.repository.PokemonRepository
import com.kartikcd.myapplication.domain.usecase.GetPokemonDetailUseCase
import com.kartikcd.myapplication.presentation.pagings.PagingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun providesPokemonRepository(
        pokemonRemoteDataSource: PokemonRemoteDataSource
    ): PokemonRepository {
        return PokemonRepositoryImpl(pokemonRemoteDataSource)
    }

    @Singleton
    @Provides
    fun providePagingRepository(
        getPokemonDetailUseCase: GetPokemonDetailUseCase
    ): PagingRepository {
        return PagingRepository(getPokemonDetailUseCase)
    }

}
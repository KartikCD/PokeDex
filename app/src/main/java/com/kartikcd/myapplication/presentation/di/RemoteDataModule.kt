package com.kartikcd.myapplication.presentation.di

import com.kartikcd.myapplication.data.api.PokemonApiService
import com.kartikcd.myapplication.data.repository.datasource.PokemonRemoteDataSource
import com.kartikcd.myapplication.data.repository.datasourceimpl.PokemonRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule {

    @Singleton
    @Provides
    fun providePokemonRemoteDataSource(
        pokemonApiService: PokemonApiService
    ): PokemonRemoteDataSource {
        return PokemonRemoteDataSourceImpl(pokemonApiService)
    }

}
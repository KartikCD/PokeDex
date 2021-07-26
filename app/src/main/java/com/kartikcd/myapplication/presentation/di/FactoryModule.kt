package com.kartikcd.myapplication.presentation.di

import android.app.Application
import com.kartikcd.myapplication.domain.usecase.GetPokemonDetailUseCase
import com.kartikcd.myapplication.domain.usecase.GetPokemonListUseCase
import com.kartikcd.myapplication.presentation.pagings.PagingRepository
import com.kartikcd.myapplication.presentation.viewmodel.MainActivityViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {

    @Singleton
    @Provides
    fun providesMainActivityViewModelFactory(
        pokemonDetailUseCase: GetPokemonDetailUseCase,
        app: Application,
        pagingRepository: PagingRepository,
        pokemonListUseCase: GetPokemonListUseCase
    ): MainActivityViewModelFactory {
        return MainActivityViewModelFactory(
            pokemonDetailUseCase, app, pagingRepository, pokemonListUseCase
        )
    }

}
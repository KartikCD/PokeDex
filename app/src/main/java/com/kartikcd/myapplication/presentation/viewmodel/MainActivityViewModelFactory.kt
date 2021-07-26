package com.kartikcd.myapplication.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kartikcd.myapplication.domain.usecase.GetPokemonDetailUseCase
import com.kartikcd.myapplication.domain.usecase.GetPokemonListUseCase
import com.kartikcd.myapplication.presentation.pagings.PagingRepository

class MainActivityViewModelFactory(
    private val pokemonDetailUseCase: GetPokemonDetailUseCase,
    private val app: Application,
    private val pagingRepository: PagingRepository,
    private val pokemonListUseCase: GetPokemonListUseCase
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainActivityViewModel(
            pokemonDetailUseCase, app, pagingRepository, pokemonListUseCase
        ) as T
    }
}
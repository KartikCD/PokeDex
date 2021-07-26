package com.kartikcd.myapplication.presentation.pagings

import android.app.Application
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.kartikcd.myapplication.domain.usecase.GetPokemonDetailUseCase
import java.util.concurrent.Flow

class PagingRepository(private val useCase: GetPokemonDetailUseCase) {
    fun getSearchResultStream(): kotlinx.coroutines.flow.Flow<PagingData<com.kartikcd.myapplication.data.model.pokemonlist.Result>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { PagingDataSource(useCase) }
        ).flow
    }
}
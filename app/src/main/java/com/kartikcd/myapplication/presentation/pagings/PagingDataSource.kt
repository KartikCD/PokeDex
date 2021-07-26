package com.kartikcd.myapplication.presentation.pagings

import android.app.Application
import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kartikcd.myapplication.data.model.pokemonlist.Result
import com.kartikcd.myapplication.domain.usecase.GetPokemonDetailUseCase
import kotlinx.coroutines.CoroutineScope
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception

class PagingDataSource(
    private val useCase: GetPokemonDetailUseCase,
): PagingSource<Int, com.kartikcd.myapplication.data.model.pokemonlist.Result>() {

    private var START_OFFSET = 0
    private val TAG = "PagingDataSource"

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {

        val position = params.key ?: START_OFFSET

        return try {
            val response = useCase.execute(position, params.loadSize)

            response.data?.let {
                val nextKey = if (it.results.isEmpty()) {
                    null
                } else {
                    position + 20
                }
                Log.i(TAG, "load: ${it.results}")
                LoadResult.Page(
                    data = it.results,
                    prevKey = if (position == START_OFFSET) null else position - 20,
                    nextKey = nextKey
                )
            }!!

        } catch (ioException: IOException) {
            return LoadResult.Error(ioException)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        } catch (e1: Exception) {
            return LoadResult.Error(e1)
        }
    }
}
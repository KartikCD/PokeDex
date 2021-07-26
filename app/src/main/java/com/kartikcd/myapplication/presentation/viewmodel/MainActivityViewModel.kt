package com.kartikcd.myapplication.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.bumptech.glide.load.engine.Resource
import com.kartikcd.myapplication.data.model.pokemondetail.PokemonDetail
import com.kartikcd.myapplication.data.model.pokemonlist.Result
import com.kartikcd.myapplication.domain.repository.PokemonRepository
import com.kartikcd.myapplication.domain.usecase.GetPokemonDetailUseCase
import com.kartikcd.myapplication.domain.usecase.GetPokemonListUseCase
import com.kartikcd.myapplication.presentation.pagings.PagingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.lang.Exception

class MainActivityViewModel(
    private val pokemonDetailUseCase: GetPokemonDetailUseCase,
    private val app: Application,
    private val pagingRepository: PagingRepository,
    private val pokemonListUseCase: GetPokemonListUseCase
) : AndroidViewModel(app) {

    private var pokemonListResult: Flow<PagingData<com.kartikcd.myapplication.data.model.pokemonlist.Result>>? =
        null

    val pokemonDetailResult: MutableLiveData<com.kartikcd.myapplication.data.util.Resource<PokemonDetail>> = MutableLiveData()

    private val TAG = "MainActivityViewModel"

    fun getPokemonList(): Flow<PagingData<Result>> {

        val lastResult = pokemonListResult
        if (lastResult != null) {
            return lastResult
        }

        val newResult: Flow<PagingData<Result>> = pagingRepository.getSearchResultStream()
            .cachedIn(viewModelScope)

        pokemonListResult = newResult
        Log.i(TAG, "getPokemonList: ${newResult}")
        return newResult
    }

    fun getPokemonDetail(pokemonName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            pokemonDetailResult.postValue(com.kartikcd.myapplication.data.util.Resource.Loading())
            try {
                if (isNetworkAvailable(app)) {
                    val apiResult = pokemonListUseCase.execute(pokemonName)
                    pokemonDetailResult.postValue(apiResult)
                } else {
                    pokemonDetailResult.postValue(com.kartikcd.myapplication.data.util.Resource.Error("Internet is not available"))
                }
            } catch (e: Exception) {
                pokemonDetailResult.postValue(com.kartikcd.myapplication.data.util.Resource.Error(e.message.toString()))
            }
        }
    }

    private fun isNetworkAvailable(context: Context?):Boolean{
        if (context == null) return false
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false

    }

}
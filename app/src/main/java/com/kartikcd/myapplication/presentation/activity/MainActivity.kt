package com.kartikcd.myapplication.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import com.akexorcist.roundcornerprogressbar.TextRoundCornerProgressBar
import com.kartikcd.myapplication.R
import com.kartikcd.myapplication.databinding.ActivityMainBinding
import com.kartikcd.myapplication.databinding.PokeminDetailBinding
import com.kartikcd.myapplication.databinding.PokemonDetailBinding
import com.kartikcd.myapplication.domain.repository.PokemonRepository
import com.kartikcd.myapplication.domain.usecase.GetPokemonDetailUseCase
import com.kartikcd.myapplication.presentation.adapter.PokemonListAdapter
import com.kartikcd.myapplication.presentation.pagings.PagingRepository
import com.kartikcd.myapplication.presentation.viewmodel.MainActivityViewModel
import com.kartikcd.myapplication.presentation.viewmodel.MainActivityViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var factory: MainActivityViewModelFactory
    lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentNavHost) as NavHostFragment
        val navController = navHostFragment.navController

        mainActivityViewModel = ViewModelProvider(this, factory)
            .get(MainActivityViewModel::class.java)


//        initRecyclerView()
    }

//    private fun initRecyclerView() {
//        binding.recyclerviewPokemonlist.apply {
//            adapter = PokemonListAdapter()
//            layoutManager = GridLayoutManager(this@MainActivity, 2)
//        }
//    }
}
package com.kartikcd.myapplication.presentation.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.kartikcd.myapplication.R
import com.kartikcd.myapplication.data.model.pokemonlist.Result
import com.kartikcd.myapplication.databinding.FragmentPokemonListBinding
import com.kartikcd.myapplication.presentation.activity.MainActivity
import com.kartikcd.myapplication.presentation.adapter.PokemonListAdapter
import com.kartikcd.myapplication.presentation.viewmodel.MainActivityViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class FragmentPokemonList : Fragment() {

    private lateinit var binding: FragmentPokemonListBinding
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var pokemonListAdapter: PokemonListAdapter

    private var searchJob: Job? = null
    private val TAG = "FragmentPokemonList"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pokemon_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPokemonListBinding.bind(view)

        viewModel = (activity as MainActivity).mainActivityViewModel
        pokemonListAdapter =
            PokemonListAdapter { pokemonName: String, id: String, colorValue: Int ->
                listItemClicked(
                    pokemonName,
                    id,
                    colorValue
                )
            }

        initRecyclerView()

        lifecycleScope.launch {
            viewModel.getPokemonList().collect {
                Log.i(TAG, "onViewCreated: Done ${it.toString()}")
                it.let { pagingData: PagingData<Result> ->
                    pokemonListAdapter.submitData(pagingData)
                }
            }
        }
    }

    private fun initRecyclerView() {
        binding.recyclerviewPokemonlist.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = pokemonListAdapter
            pokemonListAdapter.notifyDataSetChanged()
        }
    }

    private fun listItemClicked(pokemonName: String, id: String, colorValue: Int) {
        Log.i(TAG, "listItemClicked: ${pokemonName} ${id} ${colorValue}")
        val bundle = Bundle().apply {
            putSerializable("POKEMONNAME", pokemonName)
            putSerializable("POKEMONID", id)
            putSerializable("COLORVALUE", colorValue)
        }
        findNavController().navigate(
            R.id.action_fragmentPokemonList_to_fragmentPokemonDetail,
            bundle
        )
    }

}
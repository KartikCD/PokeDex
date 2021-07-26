package com.kartikcd.myapplication.presentation.fragment

import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.kartikcd.myapplication.R
import com.kartikcd.myapplication.data.util.Resource
import com.kartikcd.myapplication.databinding.FragmentPokemonDetailBinding
import com.kartikcd.myapplication.domain.util.Colors
import com.kartikcd.myapplication.presentation.activity.MainActivity
import com.kartikcd.myapplication.presentation.viewmodel.MainActivityViewModel

class FragmentPokemonDetail : Fragment() {

    private lateinit var binding: FragmentPokemonDetailBinding
    private lateinit var viewModel: MainActivityViewModel

    private val TAG = "FragmentPokemonDetail"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pokemon_detail, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPokemonDetailBinding.bind(view)
        val args: FragmentPokemonDetailArgs by navArgs()
        val pokemonName = args.POKEMONNAME
        val pokemonId = args.POKEMONID
        val colorValue = args.COLORVALUE

        viewModel = (activity as MainActivity).mainActivityViewModel

        setBackgroundOfUI(colorValue)
        setImageOfPokemon(pokemonName, pokemonId)
        setDetailsOnScreen(pokemonName)
    }

    private fun setBackgroundOfUI(colorValue: Int) {
        val gradientDrawable = GradientDrawable(
            GradientDrawable.Orientation.TOP_BOTTOM,
            intArrayOf(Colors.Black, colorValue)
        )

        gradientDrawable.cornerRadius = 0f
        binding.nestedScrollView.background = gradientDrawable
    }

    private fun setImageOfPokemon(pokemonName: String, pokemonId: String) {
        val url =
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pokemonId}.png"
        Glide
            .with(this)
            .load(url)
            .placeholder(R.drawable.loading_spinner)
            .override(135)
            .into(binding.imageviewPokemon)

        binding.textviewPokemonname.text = "#${pokemonId} ${pokemonName}"
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun setDetailsOnScreen(pokemonName: String) {
        viewModel.getPokemonDetail(pokemonName)
        viewModel.pokemonDetailResult.observe(viewLifecycleOwner) { response ->
            when(response) {
                is Resource.Success -> {
                    response.data?.let {
                        binding.textviewWeight.text = "${it.weight} Kg"
                        binding.textviewHeight.text = "${it.height} M"

                        val stats = it.stats
                        for (item in stats) {
                            val statName = item.stat.name
                            if (statName.equals("hp")) {
                                binding.progressHp.progress = item.base_stat.toFloat()
                                binding.progressHp.progressText = item.base_stat.toString()
                            } else if (statName.equals("attack")) {
                                binding.progressAtk.progressText = item.base_stat.toString()
                                binding.progressAtk.progress = item.base_stat.toFloat()
                            } else if (statName.equals("defense")) {
                                binding.progressDef.progressText = item.base_stat.toString()
                                binding.progressDef.progress = item.base_stat.toFloat()
                            } else if (statName.equals("special-attack")) {
                                binding.progressSpatk.progressText = item.base_stat.toString()
                                binding.progressSpatk.progress = item.base_stat.toFloat()
                            } else if (statName.equals("special-defense")) {
                                binding.progressSpdef.progressText = item.base_stat.toString()
                                binding.progressSpdef.progress = item.base_stat.toFloat()
                            } else if (statName.equals("speed")) {
                                binding.progressSpd.progressText = item.base_stat.toString()
                                binding.progressSpd.progress = item.base_stat.toFloat()
                            }
                        }

                        if (it.types.size > 1) {
                            binding.linear2abilities.visibility = View.VISIBLE
                            binding.textType3.visibility = View.GONE

                            for (items in 0..1) {
                                if (items == 0) {
                                    binding.textType1.text = it.types[items].type.name
                                    if (it.types[items].type.name.equals("normal")) {
                                        binding.textType1.backgroundTintList = activity?.applicationContext?.resources?.getColorStateList(R.color.TypeNormal)
                                    } else if (it.types[items].type.name.equals("fire")) {
                                        binding.textType1.backgroundTintList = activity?.applicationContext?.resources?.getColorStateList(R.color.TypeFire)
                                    } else if (it.types[items].type.name.equals("water")) {
                                        binding.textType1.backgroundTintList = activity?.applicationContext?.resources?.getColorStateList(R.color.TypeWater)
                                    } else if (it.types[items].type.name.equals("electric")) {
                                        binding.textType1.backgroundTintList = activity?.applicationContext?.resources?.getColorStateList(R.color.TypeElectric)
                                    } else if (it.types[items].type.name.equals("grass")) {
                                        binding.textType1.backgroundTintList = activity?.applicationContext?.resources?.getColorStateList(R.color.TypeGrass)
                                    } else if (it.types[items].type.name.equals("ice")) {
                                        binding.textType1.backgroundTintList = activity?.applicationContext?.resources?.getColorStateList(R.color.TypeIce)
                                    } else if (it.types[items].type.name.equals("fighting")) {
                                        binding.textType1.backgroundTintList = activity?.applicationContext?.resources?.getColorStateList(R.color.TypeFighting)
                                    } else if (it.types[items].type.name.equals("poison")) {
                                        binding.textType1.backgroundTintList = activity?.applicationContext?.resources?.getColorStateList(R.color.TypePoison)
                                    } else if (it.types[items].type.name.equals("ground")) {
                                        binding.textType1.backgroundTintList = activity?.applicationContext?.resources?.getColorStateList(R.color.TypeGround)
                                    } else if (it.types[items].type.name.equals("flying")) {
                                        binding.textType1.backgroundTintList = activity?.applicationContext?.resources?.getColorStateList(R.color.TypeFlying)
                                    } else if (it.types[items].type.name.equals("psychic")) {
                                        binding.textType1.backgroundTintList = activity?.applicationContext?.resources?.getColorStateList(R.color.TypePsychic)
                                    } else if (it.types[items].type.name.equals("bug")) {
                                        binding.textType1.backgroundTintList = activity?.applicationContext?.resources?.getColorStateList(R.color.TypeBug)
                                    } else if (it.types[items].type.name.equals("rock")) {
                                        binding.textType1.backgroundTintList = activity?.applicationContext?.resources?.getColorStateList(R.color.TypeRock)
                                    } else if (it.types[items].type.name.equals("ghost")) {
                                        binding.textType1.backgroundTintList = activity?.applicationContext?.resources?.getColorStateList(R.color.TypeGhost)
                                    } else if (it.types[items].type.name.equals("dragon")) {
                                        binding.textType1.backgroundTintList = activity?.applicationContext?.resources?.getColorStateList(R.color.TypeDragon)
                                    } else if (it.types[items].type.name.equals("dark")) {
                                        binding.textType1.backgroundTintList = activity?.applicationContext?.resources?.getColorStateList(R.color.TypeDark)
                                    } else if (it.types[items].type.name.equals("steel")) {
                                        binding.textType1.backgroundTintList = activity?.applicationContext?.resources?.getColorStateList(R.color.TypeSteel)
                                    } else if (it.types[items].type.name.equals("fairy")) {
                                        binding.textType1.backgroundTintList = activity?.applicationContext?.resources?.getColorStateList(R.color.TypeFairy)
                                    }
                                } else {
                                    binding.textType2.text = it.types[items].type.name
                                    if (it.types[items].type.name.equals("normal")) {
                                        binding.textType2.backgroundTintList = activity?.applicationContext?.resources?.getColorStateList(R.color.TypeNormal)
                                    } else if (it.types[items].type.name.equals("fire")) {
                                        binding.textType2.backgroundTintList = activity?.applicationContext?.resources?.getColorStateList(R.color.TypeFire)
                                    } else if (it.types[items].type.name.equals("water")) {
                                        binding.textType2.backgroundTintList = activity?.applicationContext?.resources?.getColorStateList(R.color.TypeWater)
                                    } else if (it.types[items].type.name.equals("electric")) {
                                        binding.textType2.backgroundTintList = activity?.applicationContext?.resources?.getColorStateList(R.color.TypeElectric)
                                    } else if (it.types[items].type.name.equals("grass")) {
                                        binding.textType2.backgroundTintList = activity?.applicationContext?.resources?.getColorStateList(R.color.TypeGrass)
                                    } else if (it.types[items].type.name.equals("ice")) {
                                        binding.textType2.backgroundTintList = activity?.applicationContext?.resources?.getColorStateList(R.color.TypeIce)
                                    } else if (it.types[items].type.name.equals("fighting")) {
                                        binding.textType2.backgroundTintList = activity?.applicationContext?.resources?.getColorStateList(R.color.TypeFighting)
                                    } else if (it.types[items].type.name.equals("poison")) {
                                        binding.textType2.backgroundTintList = activity?.applicationContext?.resources?.getColorStateList(R.color.TypePoison)
                                    } else if (it.types[items].type.name.equals("ground")) {
                                        binding.textType2.backgroundTintList = activity?.applicationContext?.resources?.getColorStateList(R.color.TypeGround)
                                    } else if (it.types[items].type.name.equals("flying")) {
                                        binding.textType2.backgroundTintList = activity?.applicationContext?.resources?.getColorStateList(R.color.TypeFlying)
                                    } else if (it.types[items].type.name.equals("psychic")) {
                                        binding.textType2.backgroundTintList = activity?.applicationContext?.resources?.getColorStateList(R.color.TypePsychic)
                                    } else if (it.types[items].type.name.equals("bug")) {
                                        binding.textType2.backgroundTintList = activity?.applicationContext?.resources?.getColorStateList(R.color.TypeBug)
                                    } else if (it.types[items].type.name.equals("rock")) {
                                        binding.textType2.backgroundTintList = activity?.applicationContext?.resources?.getColorStateList(R.color.TypeRock)
                                    } else if (it.types[items].type.name.equals("ghost")) {
                                        binding.textType2.backgroundTintList = activity?.applicationContext?.resources?.getColorStateList(R.color.TypeGhost)
                                    } else if (it.types[items].type.name.equals("dragon")) {
                                        binding.textType2.backgroundTintList = activity?.applicationContext?.resources?.getColorStateList(R.color.TypeDragon)
                                    } else if (it.types[items].type.name.equals("dark")) {
                                        binding.textType2.backgroundTintList = activity?.applicationContext?.resources?.getColorStateList(R.color.TypeDark)
                                    } else if (it.types[items].type.name.equals("steel")) {
                                        binding.textType2.backgroundTintList = activity?.applicationContext?.resources?.getColorStateList(R.color.TypeSteel)
                                    } else if (it.types[items].type.name.equals("fairy")) {
                                        binding.textType2.backgroundTintList = activity?.applicationContext?.resources?.getColorStateList(R.color.TypeFairy)
                                    }
                                }
                            }

                        } else {
                            binding.linear2abilities.visibility = View.GONE
                            binding.textType3.visibility = View.VISIBLE
                            binding.textType3.text = it.types[0].type.name
                            if (it.types[0].type.name.equals("normal")) {
                                binding.textType3.backgroundTintList = activity?.applicationContext?.resources?.getColorStateList(R.color.TypeNormal)
                            } else if (it.types[0].type.name.equals("fire")) {
                                binding.textType3.backgroundTintList = activity?.applicationContext?.resources?.getColorStateList(R.color.TypeFire)
                            } else if (it.types[0].type.name.equals("water")) {
                                binding.textType3.backgroundTintList = activity?.applicationContext?.resources?.getColorStateList(R.color.TypeWater)
                            } else if (it.types[0].type.name.equals("electric")) {
                                binding.textType3.backgroundTintList = activity?.applicationContext?.resources?.getColorStateList(R.color.TypeElectric)
                            } else if (it.types[0].type.name.equals("grass")) {
                                binding.textType3.backgroundTintList = activity?.applicationContext?.resources?.getColorStateList(R.color.TypeGrass)
                            } else if (it.types[0].type.name.equals("ice")) {
                                binding.textType3.backgroundTintList = activity?.applicationContext?.resources?.getColorStateList(R.color.TypeIce)
                            } else if (it.types[0].type.name.equals("fighting")) {
                                binding.textType3.backgroundTintList = activity?.applicationContext?.resources?.getColorStateList(R.color.TypeFighting)
                            } else if (it.types[0].type.name.equals("poison")) {
                                binding.textType3.backgroundTintList = activity?.applicationContext?.resources?.getColorStateList(R.color.TypePoison)
                            } else if (it.types[0].type.name.equals("ground")) {
                                binding.textType3.backgroundTintList = activity?.applicationContext?.resources?.getColorStateList(R.color.TypeGround)
                            } else if (it.types[0].type.name.equals("flying")) {
                                binding.textType3.backgroundTintList = activity?.applicationContext?.resources?.getColorStateList(R.color.TypeFlying)
                            } else if (it.types[0].type.name.equals("psychic")) {
                                binding.textType3.backgroundTintList = activity?.applicationContext?.resources?.getColorStateList(R.color.TypePsychic)
                            } else if (it.types[0].type.name.equals("bug")) {
                                binding.textType3.backgroundTintList = activity?.applicationContext?.resources?.getColorStateList(R.color.TypeBug)
                            } else if (it.types[0].type.name.equals("rock")) {
                                binding.textType3.backgroundTintList = activity?.applicationContext?.resources?.getColorStateList(R.color.TypeRock)
                            } else if (it.types[0].type.name.equals("ghost")) {
                                binding.textType3.backgroundTintList = activity?.applicationContext?.resources?.getColorStateList(R.color.TypeGhost)
                            } else if (it.types[0].type.name.equals("dragon")) {
                                binding.textType3.backgroundTintList = activity?.applicationContext?.resources?.getColorStateList(R.color.TypeDragon)
                            } else if (it.types[0].type.name.equals("dark")) {
                                binding.textType3.backgroundTintList = activity?.applicationContext?.resources?.getColorStateList(R.color.TypeDark)
                            } else if (it.types[0].type.name.equals("steel")) {
                                binding.textType3.backgroundTintList = activity?.applicationContext?.resources?.getColorStateList(R.color.TypeSteel)
                            } else if (it.types[0].type.name.equals("fairy")) {
                                binding.textType3.backgroundTintList = activity?.applicationContext?.resources?.getColorStateList(R.color.TypeFairy)
                            }
                        }
                    }
                }
            }
        }
    }
}
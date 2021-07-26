package com.kartikcd.myapplication.presentation.adapter

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.kartikcd.myapplication.R
import com.kartikcd.myapplication.data.model.pokemonlist.Result
import com.kartikcd.myapplication.databinding.PokemonListBinding
import com.kartikcd.myapplication.domain.util.Colors
import java.lang.Exception
import java.net.URL

class PokemonListViewHoler(val binding: PokemonListBinding) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup): PokemonListViewHoler {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.pokemon_list, parent, false)
            val binding = PokemonListBinding.bind(view)
            return PokemonListViewHoler(binding)
        }

        private const val TAG = "PokemonListViewHoler"
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun bind(result: Result, context: Context, clickListener: (String, String, Int) -> Unit) {
        try {

            var colorValues: Int = 0

            val SIMPLETARGET = object : SimpleTarget<Drawable>() {
                override fun onResourceReady(
                    resource: Drawable,
                    transition: Transition<in Drawable>?
                ) {
                    binding.imageviewPokemon.foreground = resource
                    val bmp = (binding.imageviewPokemon.foreground as BitmapDrawable).bitmap
                    Palette.from(bmp).generate {
                        it?.dominantSwatch?.rgb?.let { colorValue ->
                            colorValues = colorValue
                            val gradientDrawable = GradientDrawable(
                                GradientDrawable.Orientation.TOP_BOTTOM,
                                intArrayOf(colorValue, Colors.White)
                            )

                            gradientDrawable.cornerRadius = 0f
                            binding.imageviewPokemon.background = gradientDrawable
                        }
                    }
                }
            }

            val number = if (result.url.endsWith("/")) {
                result.url.dropLast(1).takeLastWhile { it.isDigit() }
            } else {
                result.url.takeLastWhile { it.isDigit() }
            }

            val url =
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${number}.png"

            Glide
                .with(context)
                .load(url)
                .placeholder(R.drawable.loading_spinner)
                .into(SIMPLETARGET)

            binding.textviewPokemonname.text = result.name

            binding.cardviewPokemonlist.setOnClickListener {
                clickListener(result.name, number, colorValues)
            }

        } catch (e: Exception) {
            Log.i(TAG, "${e.message}")
        }
    }


}


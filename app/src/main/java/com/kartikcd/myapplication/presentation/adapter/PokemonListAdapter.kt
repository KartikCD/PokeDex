package com.kartikcd.myapplication.presentation.adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.ColorSpace
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.net.UrlQuerySanitizer
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.paging.PagingDataAdapter
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.kartikcd.myapplication.R
import com.kartikcd.myapplication.data.model.pokemonlist.Result
import com.kartikcd.myapplication.databinding.PokemonListBinding
import com.kartikcd.myapplication.domain.util.Colors
import java.lang.Exception
import java.net.URL
import kotlin.coroutines.coroutineContext

class PokemonListAdapter(private  val clickListener: (String, String, Int) -> Unit): PagingDataAdapter<Result, PokemonListViewHoler>(
    COMPARATOR) {

    private val TAG = "PokemonListAdapter"

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Result>() {
            override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean =
                oldItem == newItem
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: PokemonListViewHoler, position: Int) {
        getItem(position)?.let {

            (holder as PokemonListViewHoler).bind(it, holder.itemView.context, clickListener)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonListViewHoler {
        return PokemonListViewHoler.create(parent)
    }
}
package com.example.pokedex.ui.pokemonList

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.pokedex.network.models.PokemonResult

class PokemonListAdapter: PagedListAdapter<PokemonResult, PokemonListViewHolder>(POKEMON_COMPARATOR) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonListViewHolder {
        return PokemonListViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: PokemonListViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    companion object {
        private val POKEMON_COMPARATOR = object: DiffUtil.ItemCallback<PokemonResult>() {
            override fun areItemsTheSame(oldItem: PokemonResult, newItem: PokemonResult): Boolean =
                oldItem.name == newItem.name

            override fun areContentsTheSame( oldItem: PokemonResult, newItem: PokemonResult): Boolean =
                oldItem.name == newItem.name
        }
    }
}
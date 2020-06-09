package com.example.pokedex.ui.pokemonList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokedex.R
import com.example.pokedex.network.models.PokemonResult
import kotlinx.android.synthetic.main.item_pokemon.view.*

class PokemonListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun bind(pokemon: PokemonResult) {
        itemView.pokemon_name.text = pokemon.name.capitalize()
        itemView.pokemon_id.text = pokemon.id().padStart(3, '0')

        itemView.cardView.setOnClickListener {
            val pokemonId = pokemon.id().toInt()

            val action = PokemonListFragmentDirections.goToDetailsPokemon(pokemonId)
            itemView.findNavController().navigate(action)
        }

        Glide.with(itemView)
            .load(pokemon.getImagePath())
            .centerCrop()
            .into(itemView.pokemon_image)
    }

    companion object {
        fun create(parent: ViewGroup): PokemonListViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_pokemon, parent, false)

            return PokemonListViewHolder(view)
        }
    }
}
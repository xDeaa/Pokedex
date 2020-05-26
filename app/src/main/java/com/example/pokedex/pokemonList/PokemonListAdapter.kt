package com.example.pokedex.pokemonList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokedex.R
import com.example.pokedex.data.Pokemons
import kotlinx.android.synthetic.main.item_pokemon.view.*
import kotlin.properties.Delegates

class PokemonListAdapter : RecyclerView.Adapter<PokemonListAdapter.PokemonViewHolder>() {

    var list: List<Pokemons> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }
    var onClickListener: (String) -> Unit = { }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pokemon, parent, false)

        return PokemonViewHolder(itemView)
    }

    inner class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(pokemon: Pokemons) {
            val number = pokemon.details.split("/")[6]
            itemView.cardView.setOnClickListener {
                onClickListener.invoke(number)
            }

            val numberPokemon = number.padStart(3, '0')
            itemView.pokemon_name.text = pokemon.name.capitalize()
            itemView.pokemon_number.text = numberPokemon

            Glide.with(this.itemView)
                .load("https://assets.pokemon.com/assets/cms2/img/pokedex/full/${numberPokemon}.png")
                .centerCrop().into(this.itemView.pokemon_image)
        }
    }
}
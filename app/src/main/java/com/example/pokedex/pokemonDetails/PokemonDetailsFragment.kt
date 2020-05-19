package com.example.pokedex.pokemonDetails

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide

import com.example.pokedex.R
import com.example.pokedex.databinding.FragmentDetailsPokemonBinding
import com.example.pokedex.pokemonList.PokemonViewModel
import kotlinx.android.synthetic.main.fragment_details_pokemon.*
import kotlinx.android.synthetic.main.item_pokemon.pokemon_name

/**
 * A simple [Fragment] subclass.
 */
class PokemonDetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsPokemonBinding
    private val args: PokemonDetailsFragmentArgs by navArgs()

    private val viewModel by lazy {
        ViewModelProvider(activity as AppCompatActivity).get(PokemonViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getPokemonDetails(args.pokemonNumber.orEmpty())
        viewModel.getPokemonSpecies(args.pokemonNumber.orEmpty())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details_pokemon, container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.pokemonDetails.observe(this, Observer { pokemonDetail ->
            pokemon_number.text = "#${args.pokemonNumber?.padStart(3, '0')}"
            pokemon_name.text = pokemonDetail.name.capitalize()
            pokemon_exp.text = "Base Exp : ${pokemonDetail.exp}"
            exp.progress = pokemonDetail.exp
            pokemon_height.text = pokemonDetail.height.toString()
            pokemon_weight.text = pokemonDetail.weight.toString()
           /* pokemon_height.text = pokemonDetail.height.toString()
            pokemon_width.text = pokemonDetail.weight.toString()
            pokemon_exp.text = pokemonDetail.exp.toString()
            pokemonDetail.abilities.forEach { abilities ->
                pokemon_abilities.text = "${pokemon_abilities.text}, ${abilities.ability.name}"
            }
            pokemonDetail.types.forEach { types ->
                Log.i("types", types.toString())
                pokemon_types.text = "${pokemon_types.text}, ${types.type.name}"
            }*/
        })

        viewModel.pokemonSpecies.observe(this, Observer { pokemonSpecies ->
            val background = resources.getIdentifier("bg_${pokemonSpecies.color.name}", "mipmap", "com.example.pokedex")
            frameLayout.setBackgroundResource(background)

            pokemon_shape.text = pokemonSpecies.shape.name.capitalize()
            /* pokemon_height.text = pokemonDetail.height.toString()
             pokemon_width.text = pokemonDetail.weight.toString()
             pokemon_exp.text = pokemonDetail.exp.toString()
             pokemonDetail.abilities.forEach { abilities ->
                 pokemon_abilities.text = "${pokemon_abilities.text}, ${abilities.ability.name}"
             }
             pokemonDetail.types.forEach { types ->
                 Log.i("types", types.toString())
                 pokemon_types.text = "${pokemon_types.text}, ${types.type.name}"
             }*/
        })

        binding.apply {


            //frameLayout.background = R.mipmap.bg_red
            Glide.with(view)
                .load("https://assets.pokemon.com/assets/cms2/img/pokedex/full/${args.pokemonNumber?.padStart(3, '0')}.png")
                .centerCrop().into(pokemonImage)
        }
    }
}

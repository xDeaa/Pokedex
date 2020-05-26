package com.example.pokedex.pokemonDetails

import android.annotation.SuppressLint
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


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.pokemonDetails.observe(this, Observer { pokemonDetail ->
            pokemon_number.text = "#${args.pokemonNumber?.padStart(3, '0')}"
            pokemon_name.text = pokemonDetail.name.capitalize()
            pokemon_exp.text = "${pokemonDetail.exp}"
            exp.progress = pokemonDetail.exp
            pokemon_height.text = "${pokemonDetail.height.toDouble().times(10).div(100)} cm"
            pokemon_weight.text = "${pokemonDetail.weight.toDouble().times(10).div(100)}"

            val type1 = resources.getIdentifier("pokemon_types_${pokemonDetail.types[0].type.name}", "drawable", "com.example.pokedex")
            pokemon_type_1.setImageResource(type1)

            if(pokemonDetail.types.size > 1) {
                val type2 = resources.getIdentifier("pokemon_types_${pokemonDetail.types[1].type.name}", "drawable", "com.example.pokedex")
                pokemon_type_2.setImageResource(type2)
            } else {
                pokemon_type_2.setImageResource(0)
            }

            pokemon_abilities_1.text = pokemonDetail.abilities[0].ability.name.capitalize()

            if(pokemonDetail.abilities.size > 1) {
                pokemon_abilities_2.text = ", ${pokemonDetail.abilities[1].ability.name.capitalize()}"
            } else {
                pokemon_abilities_2.text = ""
            }

            pokemonDetail.stats.forEach { stats ->
                when(stats.stat.name) {
                    "hp" -> {
                        pokemon_hp.text = stats.base_stat.toString()
                        pokemon_exp_hp.progress = stats.base_stat
                    }
                    "attack" -> {
                        pokemon_attack.text = stats.base_stat.toString()
                        pokemon_exp_attack.progress = stats.base_stat
                    }
                    "defense" -> {
                        pokemon_defense.text = stats.base_stat.toString()
                        pokemon_exp_defense.progress = stats.base_stat
                    }
                    "special-attack" -> {
                        pokemon_sp_atk.text = stats.base_stat.toString()
                        pokemon_exp_sp_atk.progress = stats.base_stat
                    }
                    "special-defense" -> {
                        pokemon_sp_def.text = stats.base_stat.toString()
                        pokemon_exp_sp_def.progress = stats.base_stat
                    }
                    "speed" -> {
                        pokemon_speed.text = stats.base_stat.toString()
                        pokemon_exp_speed.progress = stats.base_stat
                    }
                }
            }
        })

        viewModel.pokemonSpecies.observe(this, Observer { pokemonSpecies ->
            val background = resources.getIdentifier("bg_${pokemonSpecies.color.name}", "drawable", "com.example.pokedex")
            frameLayout.setBackgroundResource(background)

            pokemon_shape.text = pokemonSpecies.shape.name.capitalize()
            pokemon_habitat.text = pokemonSpecies.habitat.name.capitalize()
            pokemon_capture.text = pokemonSpecies.capture.toString()
            pokemon_happiness.text = pokemonSpecies.happiness.toString()

            pokemon_egg_1.text = pokemonSpecies.groups[0].name.capitalize()

            if(pokemonSpecies.groups.size > 1) {
                pokemon_egg_2.text = ", ${pokemonSpecies.groups[1].name.capitalize()}"
            } else {
                pokemon_egg_2.text = ""
            }
        })

        binding.apply {
            val pokemonBuilder = Glide.with(view).load("https://assets.pokemon.com/assets/cms2/img/pokedex/full/${args.pokemonNumber?.padStart(3, '0')}.png")
                .centerCrop()

            pokemonBuilder.into(pokemonImage)
            pokemonBuilder.into(pokemonImgHeight)
            pokemonBuilder.into(pokemonImgWeight)
        }
    }
}

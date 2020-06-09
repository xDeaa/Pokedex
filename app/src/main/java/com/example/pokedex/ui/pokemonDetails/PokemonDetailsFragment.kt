package com.example.pokedex.ui.pokemonDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.pokedex.R
import com.example.pokedex.databinding.FragmentDetailsPokemonBinding
import kotlinx.android.synthetic.main.fragment_details_pokemon.*
import kotlinx.android.synthetic.main.item_pokemon.pokemon_id
import kotlinx.android.synthetic.main.item_pokemon.pokemon_name

class PokemonDetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsPokemonBinding
    private val args: PokemonDetailsFragmentArgs by navArgs()

    private val viewModel by lazy {
        ViewModelProvider(activity as AppCompatActivity).get(PokemonDetailsViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()

        viewModel.loadPokemon(args.pokemonId)
        viewModel.loadPokemonSpecies(args.pokemonId)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_details_pokemon,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.pokemon.observe(viewLifecycleOwner, Observer {
            val pokemonId = args.pokemonId.toString()

            pokemon_id.text = "#${pokemonId.padStart(3, '0')}"
            pokemon_name.text = it.name.capitalize()
            pokemon_experience.text = it.baseExperience.toString()
            experience.progress = it.baseExperience
            pokemon_height.text = "${it.height.toDouble().times(10).div(100)} cm"
            pokemon_weight.text = "${it.weight.toDouble().times(10).div(100)}"

            val firstType = it.types[0].type.name
            pokemon_type_1.setImageResource(
                resources.getIdentifier(
                    firstType,
                    "drawable",
                    "com.example.pokedex"
                )
            )

            pokemon_type_2.setImageResource(0)
            // If pokemon got two Types
            if (it.types.size > 1) {
                val secondType = it.types[1].type.name
                pokemon_type_2.setImageResource(
                    resources.getIdentifier(
                        secondType,
                        "drawable",
                        "com.example.pokedex"
                    )
                )
            }

            val firstAbility = it.abilities[0].ability.name
            pokemon_abilities_1.text = firstAbility.capitalize()

            pokemon_abilities_2.text = ""
            // If pokemon got more than one Ability
            if (it.abilities.size > 1) {
                val secondAbility = it.abilities[1].ability.name
                pokemon_abilities_2.text = ", ${secondAbility.capitalize()}"
            }

            it.stats.forEach {
                val baseStat = it.baseStat

                when(it.stat.name) {
                    "hp" -> {
                        pokemon_hp.text = baseStat.toString()
                        pokemon_exp_hp.progress = baseStat
                    }
                    "attack" -> {
                        pokemon_attack.text = baseStat.toString()
                        pokemon_exp_attack.progress = baseStat
                    }
                    "defense" -> {
                        pokemon_defense.text = baseStat.toString()
                        pokemon_exp_defense.progress = baseStat
                    }
                    "special-attack" -> {
                        pokemon_sp_atk.text = baseStat.toString()
                        pokemon_exp_sp_atk.progress = baseStat
                    }
                    "special-defense" -> {
                        pokemon_sp_def.text = baseStat.toString()
                        pokemon_exp_sp_def.progress = baseStat
                    }
                    "speed" -> {
                        pokemon_speed.text = baseStat.toString()
                        pokemon_exp_speed.progress = baseStat
                    }
                }
            }

            viewModel.species.observe(viewLifecycleOwner, Observer {
                val background = "bg_${it.color.name}"

                frameLayout.setBackgroundResource(
                    resources.getIdentifier(
                        background,
                        "drawable",
                        "com.example.pokedex"
                    )
                )

                pokemon_shape.text = it.shape.name.capitalize()
                pokemon_habitat.text = it.habitat.name.capitalize()
                pokemon_capture.text = it.captureRate.toString()
                pokemon_happiness.text = it.baseHappiness.toString()

                val firstEggGroup = it.eggGroups[0].name
                pokemon_egg_1.text = firstEggGroup.capitalize()

                pokemon_egg_2.text = ""
                // If pokmeon got more than one Egg Group
                if (it.eggGroups.size > 1) {
                    val secondEggGroup = it.eggGroups[1].name
                    pokemon_egg_2.text = secondEggGroup
                }

                val pokemonImageURL = "https://assets.pokemon.com/assets/cms2/img/pokedex/full/${pokemonId.padStart(3, '0')}.png"
                val imageBuilder = Glide.with(view)
                    .load(pokemonImageURL)
                    .centerCrop()

                binding.apply {
                    imageBuilder.into(pokemonImage)
                    imageBuilder.into(pokemonImgHeight)
                    imageBuilder.into(pokemonImgWeight)
                }
            })
        })
    }
}
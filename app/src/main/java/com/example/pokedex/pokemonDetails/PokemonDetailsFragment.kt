package com.example.pokedex.pokemonDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide

import com.example.pokedex.R
import com.example.pokedex.databinding.FragmentPokemonDetailsBinding
import com.example.pokedex.pokemonList.PokemonViewModel
import kotlinx.android.synthetic.main.item_pokemon.view.*

/**
 * A simple [Fragment] subclass.
 */
class PokemonDetailsFragment : Fragment() {

    private lateinit var binding: FragmentPokemonDetailsBinding
    private val args: PokemonDetailsFragmentArgs by navArgs()

    private val viewModel by lazy {
        ViewModelProvider(activity as AppCompatActivity).get(PokemonViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getPokemonDetails(args.pokemonNumber.orEmpty())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pokemon_details, container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            Glide.with(view)
                .load("https://assets.pokemon.com/assets/cms2/img/pokedex/full/${args.pokemonNumber?.padStart(3, '0')}.png")
                .circleCrop().into(pokemonImage)
        }
    }
}

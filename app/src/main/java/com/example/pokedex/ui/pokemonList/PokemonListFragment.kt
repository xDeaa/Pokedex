package com.example.pokedex.ui.pokemonList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pokedex.R
import com.example.pokedex.databinding.FragmentPokemonListBinding
import kotlinx.android.synthetic.main.item_pokemon.view.*

class PokemonListFragment : Fragment() {
    private lateinit var binding: FragmentPokemonListBinding
    private var pokemonListAdapter: PokemonListAdapter = PokemonListAdapter()

    private val viewModel by lazy {
        ViewModelProvider(activity as AppCompatActivity).get(PokemonListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_pokemon_list,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gridManager = GridLayoutManager(this.context, 2)
        binding.apply {
            recyclerView.layoutManager = gridManager
            recyclerView.adapter = pokemonListAdapter

            viewModel.pokemons.observe(viewLifecycleOwner, Observer {
                pokemonListAdapter.submitList(it)
            })
        }
    }
}
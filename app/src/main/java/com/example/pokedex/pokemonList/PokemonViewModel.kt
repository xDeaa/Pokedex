package com.example.pokedex.pokemonList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokedex.data.PokemonDetails
import com.example.pokedex.data.Pokemons
import com.example.pokedex.data.PokemonsResponse
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class PokemonViewModel: ViewModel() {
    private val viewModelScope = MainScope()

    private val repository = PokemonRepository()
    private val _pokemonsList = MutableLiveData<List<Pokemons>>()
    val pokemonList: LiveData<List<Pokemons>> = _pokemonsList

    private val _pokemonDetails = MutableLiveData<PokemonDetails>()
    val pokemonDetails: LiveData<PokemonDetails> = _pokemonDetails



    fun loadPokemons() {
        viewModelScope.launch {
            val pokemons: List<Pokemons> = repository.getPokemons()?.pokemons.orEmpty()
            _pokemonsList.postValue(pokemons)
        }
    }

    fun getPokemonDetails(number: String) {
        viewModelScope.launch {
            val pokemon: PokemonDetails = repository.getPokemonsDetails(number)!!
            _pokemonDetails.postValue(pokemon)
        }
    }
}
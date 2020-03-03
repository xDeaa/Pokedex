package com.example.pokedex.pokemonList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokedex.data.Pokemons
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class PokemonViewModel: ViewModel() {
    private val viewModelScope = MainScope()

    private val repository = PokemonRepository()
    private val _pokemonsList = MutableLiveData<List<Pokemons>>()
    val pokemonList: LiveData<List<Pokemons>> = _pokemonsList

    fun loadPokemons() {
        viewModelScope.launch {
            _pokemonsList.postValue(repository.getPokemons())
        }
    }
}
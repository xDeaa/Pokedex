package com.example.pokedex.ui.pokemonDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokedex.network.models.Pokemon
import com.example.pokedex.network.models.PokemonSpecies
import com.example.pokedex.repository.PokemonRepository
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class PokemonDetailsViewModel: ViewModel()  {
    private val viewModelScope = MainScope()
    private val repository = PokemonRepository()

    private val _pokemon = MutableLiveData<Pokemon>()
    val pokemon: LiveData<Pokemon> = _pokemon

    private val _species = MutableLiveData<PokemonSpecies>()
    val species: LiveData<PokemonSpecies> = _species

    fun loadPokemon(id: Int) {
        viewModelScope.launch {
            repository.getPokemon(id)?.let {
                _pokemon.value = it
            }
        }
    }

    fun loadPokemonSpecies(id: Int) {
        viewModelScope.launch {
            repository.getPokemonSpecies(id)?.let {
                _species.value = it
            }
        }
    }
}
package com.example.pokedex.ui.pokemonList

import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import com.example.pokedex.network.models.PokemonResult
import com.example.pokedex.repository.PokemonPageKeyedDataSource
import kotlinx.coroutines.MainScope

class PokemonListViewModel: ViewModel()  {
    private val viewModelScope = MainScope()

    val pokemons = LivePagedListBuilder(object : DataSource.Factory<Int, PokemonResult>() {
        override fun create(): DataSource<Int, PokemonResult> = PokemonPageKeyedDataSource(viewModelScope)
    }, PER_PAGE).build()

    companion object {
        private const val PER_PAGE = 20
    }
}




/*

package com.example.pokedex.pokemonList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokedex.data.PokemonDetails
import com.example.pokedex.data.PokemonSpecies
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

    private val _pokemonSpecies = MutableLiveData<PokemonSpecies>()
    val pokemonSpecies: LiveData<PokemonSpecies> = _pokemonSpecies

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

    fun getPokemonSpecies(number: String) {
        viewModelScope.launch {
            val pokemon: PokemonSpecies = repository.getPokemonsSpecies(number)!!
            _pokemonSpecies.postValue(pokemon)
        }
    }
}

 */
package com.example.pokedex.repository

import androidx.paging.PageKeyedDataSource
import com.example.pokedex.network.Api
import com.example.pokedex.network.models.PokemonResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class PokemonPageKeyedDataSource(
    private val scope: CoroutineScope
): PageKeyedDataSource<Int, PokemonResult>() {
    private val pokemonWebService = Api.INSTANCE.pokemonWebService

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int,PokemonResult>
    ) {}

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, PokemonResult>
    ) {
        scope.launch {
            val pokemons = pokemonWebService.getPokemonList(0).body()!!.results

            callback.onResult(pokemons, 0, 1)
        }
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, PokemonResult>
    ) {
        scope.launch {
            val limit = 20
            val offset = params.key * limit

            val pokemons = pokemonWebService.getPokemonList(offset, limit).body()!!.results

            callback.onResult(pokemons, params.key + 1)
        }
    }
}

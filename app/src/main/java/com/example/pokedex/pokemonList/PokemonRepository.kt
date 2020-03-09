package com.example.pokedex.pokemonList

import com.example.pokedex.data.PokemonsResponse
import com.example.pokedex.network.Api

class PokemonRepository {
    private val pokemonWebServices = Api.INSTANCE.pokemonWebServices

    suspend fun getPokemons(): PokemonsResponse? {
        val response = pokemonWebServices.getPokemons()

        return if (!response.pokemons.isEmpty()) response else null
    }
}
package com.example.pokedex.pokemonList

import com.example.pokedex.data.Pokemons
import com.example.pokedex.network.Api

class PokemonRepository {
    private val pokemonWebServices = Api.INSTANCE.pokemonWebServices

    suspend fun getPokemons(): List<Pokemons>? {
        val response = pokemonWebServices.getPokemons()

        return if (response.isSuccessful) response.body() else null
    }
}
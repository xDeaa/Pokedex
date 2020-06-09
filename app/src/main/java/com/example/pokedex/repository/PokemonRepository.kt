package com.example.pokedex.repository

import com.example.pokedex.network.Api
import com.example.pokedex.network.models.Pokemon
import com.example.pokedex.network.models.PokemonSpecies

class PokemonRepository {
    private val pokemonWebService = Api.INSTANCE.pokemonWebService

    suspend fun getPokemon(id: Int): Pokemon? {
        val response = pokemonWebService.getPokemonDetails(id)

        return if (response.isSuccessful) response.body() else null
    }

    suspend fun getPokemonSpecies(id: Int): PokemonSpecies? {
        val response = pokemonWebService.getPokemonSpecies(id)

        return if (response.isSuccessful) response.body() else null
    }
}
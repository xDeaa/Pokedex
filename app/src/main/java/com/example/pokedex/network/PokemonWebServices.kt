package com.example.pokedex.network

import com.example.pokedex.data.Pokemons
import com.example.pokedex.data.PokemonsResponse
import retrofit2.Response
import retrofit2.http.*

interface PokemonWebServices {
    @GET("pokemon-species")
    suspend fun getPokemons(): PokemonsResponse
}
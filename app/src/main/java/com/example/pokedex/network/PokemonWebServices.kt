package com.example.pokedex.network

import com.example.pokedex.data.Pokemons
import retrofit2.Response
import retrofit2.http.*

interface PokemonWebServices {
    @GET("pokemon-species")
    suspend fun getPokemons(): Response<List<Pokemons>>
}
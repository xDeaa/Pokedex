package com.example.pokedex.network

import com.example.pokedex.data.PokemonDetails
import com.example.pokedex.data.PokemonSpecies
import com.example.pokedex.data.Pokemons
import com.example.pokedex.data.PokemonsResponse
import retrofit2.Response
import retrofit2.http.*

interface PokemonWebServices {
    @GET("pokemon-species")
    suspend fun getPokemons(): PokemonsResponse

    @GET("pokemon/{number}")
    suspend fun getPokemonDetails(@Path("number") number: String): PokemonDetails

    @GET("pokemon-species/{number}")
    suspend fun getPokemonSpecies(@Path("number") number: String): PokemonSpecies
}
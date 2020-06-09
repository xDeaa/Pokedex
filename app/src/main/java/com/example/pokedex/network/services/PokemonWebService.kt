package com.example.pokedex.network.services

import com.example.pokedex.network.models.PokemonSpecies
import com.example.pokedex.network.models.Pokemon
import com.example.pokedex.network.models.PokemonList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonWebService {
    @GET("pokemon-species")
    suspend fun getPokemonList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int = 20
    ): Response<PokemonList>

    @GET("pokemon/{id}")
    suspend fun getPokemonDetails(
        @Path("id") id: Int
    ): Response<Pokemon>

    @GET("pokemon-species/{id}")
    suspend fun getPokemonSpecies(
        @Path("id") id: Int
    ): Response<PokemonSpecies>
}
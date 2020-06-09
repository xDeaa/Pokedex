package com.example.pokedex.network

import android.content.Context
import com.example.pokedex.network.services.PokemonWebService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class Api(private val context: Context) {
    companion object {
        private const val BASE_URL = "https://pokeapi.co/api/v2/"
        lateinit var INSTANCE: Api
    }

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val pokemonWebService: PokemonWebService by lazy { retrofit.create(PokemonWebService::class.java) }
}
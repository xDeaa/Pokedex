package com.example.pokedex.network

import android.content.Context
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class Api(private val context: Context) {
    companion object {
        private const val BASE_URL = "https://pokeapi.co/api/v2/"
        lateinit var INSTANCE: Api
    }

    private val moshi = Moshi.Builder().build()
    private val okHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                val newRequest = chain.request().newBuilder()
                    .build()
                chain.proceed(newRequest)
            }
            .build()
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val pokemonWebServices: PokemonWebServices by lazy { retrofit.create(PokemonWebServices::class.java) }
}
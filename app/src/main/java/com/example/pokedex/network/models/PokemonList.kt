package com.example.pokedex.network.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class PokemonList (
    @field:Json(name = "count")
    val count: Int,

    @field:Json(name = "results")
    var results: List<PokemonResult>
)
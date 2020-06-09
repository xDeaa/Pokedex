package com.example.pokedex.network.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonSpecies(
    @Json(name = "base_happiness")
    var baseHappiness: Int,

    @Json(name = "capture_rate")
    var captureRate: Int,

    @Json(name = "color")
    var color: NameUrl,

    @Json(name = "egg_groups")
    var eggGroups: List<NameUrl>,

    @Json(name = "habitat")
    var habitat: NameUrl,

    @Json(name = "shape")
    var shape: NameUrl
)
package com.example.pokedex.network.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Pokemon(
    @Json(name = "name")
    var name: String,

    @Json(name = "base_experience")
    var baseExperience: Int,

    @Json(name = "height")
    var height: Int,

    @Json(name = "weight")
    var weight: Int,

    @field:Json(name = "types")
    var types: List<Type>,

    @Json(name = "abilities")
    val abilities: List<Ability>,

    @Json(name = "stats")
    val stats: List<Stat>
)

@JsonClass(generateAdapter = true)
data class Type(
    @Json(name = "slot")
    val slot: Int,

    @Json(name = "type")
    val type: NameUrl
)

@JsonClass(generateAdapter = true)
data class Ability(
    @Json(name = "ability")
    val ability: NameUrl
)

data class Stat(
    @Json(name = "base_stat")
    val baseStat: Int,

    @Json(name = "stat")
    val stat: NameUrl
)

@JsonClass(generateAdapter = true)
data class NameUrl(
    @Json(name = "name")
    val name: String,

    @Json(name = "url")
    val url: String
)

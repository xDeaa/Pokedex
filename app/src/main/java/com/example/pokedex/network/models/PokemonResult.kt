package com.example.pokedex.network.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class PokemonResult(
    @field:Json(name = "name")
    var name: String,

    @field:Json(name = "url")
    var url: String
) {
    fun id(): String {
        return url.removeSuffix("/").split("/").last()
    }

    fun getImagePath(): String {
        return "https://pokeres.bastionbot.org/images/pokemon/${id()}.png"
    }
}
package com.example.pokedex.data

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
@JsonClass(generateAdapter = true)
data class PokemonDetails(
    @field:Json(name = "name")
    var name: String,
    @field:Json(name = "height")
    var height: Int,
    @field:Json(name = "weight")
    var weight: Int,
    @field:Json(name = "base_experience")
    var exp: Int,
    @field:Json(name = "types")
    var types: List<PokemonTypes>,
    @field:Json(name = "abilities")
    var abilities: List<PokemonAbilities>,
    @field:Json(name = "stats")
    var stats: List<PokemonStats>

) : Serializable, Parcelable
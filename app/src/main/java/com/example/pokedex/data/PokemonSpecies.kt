package com.example.pokedex.data

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
@JsonClass(generateAdapter = true)
data class PokemonSpecies(
    @field:Json(name = "base_happiness")
    var happiness: Int,
    @field:Json(name = "capture_rate")
    var capture: Int,
    @field:Json(name = "color")
    var color: PokemonDetailsName,
    @field:Json(name = "egg_groups")
    var groups: List<PokemonDetailsName>,
    @field:Json(name = "evolves_from_species")
    var evol: PokemonDetailsName?,
    @field:Json(name = "habitat")
    var habitat: PokemonDetailsName,
    @field:Json(name = "shape")
    var shape: PokemonDetailsName

) : Serializable, Parcelable
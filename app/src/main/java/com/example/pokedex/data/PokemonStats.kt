package com.example.pokedex.data

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
@JsonClass(generateAdapter = true)
data class PokemonStats (
    @field:Json(name="base_stat")
    var base_stat: Int,
    @field:Json(name="stat")
    var stat: PokemonDetailsName
): Serializable, Parcelable
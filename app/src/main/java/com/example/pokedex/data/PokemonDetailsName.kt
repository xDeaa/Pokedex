package com.example.pokedex.data

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
@JsonClass(generateAdapter = true)
data class PokemonDetailsName (
    @field:Json(name="name")
    var name: String
): Serializable, Parcelable
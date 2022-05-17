package com.example.data.remote.dtos.character

import com.example.domain.model.character.LocationModel
import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)

fun Location.toDomain() = LocationModel(
    name, url
)
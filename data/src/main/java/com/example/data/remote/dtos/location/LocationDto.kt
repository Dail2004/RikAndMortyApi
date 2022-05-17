package com.example.data.remote.dtos.location

import com.example.domain.model.location.LocationsModel
import com.google.gson.annotations.SerializedName

data class LocationDto(
    @SerializedName("created")
    val created: String,
    @SerializedName("dimension")
    val dimension: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("residents")
    val residents: List<Any>,
    @SerializedName("type")
    val type: String,
    @SerializedName("url")
    val url: String
)

fun LocationDto.toDomain() = LocationsModel(
    created, dimension, id, name, residents, type, url
)
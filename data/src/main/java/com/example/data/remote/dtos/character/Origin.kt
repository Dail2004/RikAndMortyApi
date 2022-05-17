package com.example.data.remote.dtos.character

import com.example.domain.model.character.OriginModel
import com.google.gson.annotations.SerializedName

data class Origin(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)

fun Origin.toDomain() = OriginModel(
    name, url
)
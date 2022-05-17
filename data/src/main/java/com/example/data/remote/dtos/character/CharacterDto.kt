package com.example.data.remote.dtos.character

import com.example.domain.model.character.CharactersModel
import com.google.gson.annotations.SerializedName

data class CharacterDto(
    @SerializedName("created")
    val created: String,
    @SerializedName("episode")
    val episode: List<String>,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("location")
    val location: Location,
    @SerializedName("name")
    val name: String,
    @SerializedName("origin")
    val origin: Origin,
    @SerializedName("species")
    val species: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("url")
    val url: String
)

fun CharacterDto.toDomain() = CharactersModel(
    created,
    episode,
    gender,
    id,
    image,
    location.toDomain(),
    name,
    origin.toDomain(),
    species,
    status,
    type,
    url
)
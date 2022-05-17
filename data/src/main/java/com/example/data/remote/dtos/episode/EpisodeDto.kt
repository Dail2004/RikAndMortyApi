package com.example.data.remote.dtos.episode

import com.example.domain.model.episode.EpisodesModel
import com.google.gson.annotations.SerializedName

data class EpisodeDto(
    @SerializedName("air_date")
    val air_date: String,
    @SerializedName("characters")
    val characters: List<Any>,
    @SerializedName("created")
    val created: String,
    @SerializedName("episode")
    val episode: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)

fun EpisodeDto.toDomain() = EpisodesModel(
    air_date, characters, created, episode, id, name, url
)
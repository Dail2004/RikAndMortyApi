package com.example.domain.model.episode

data class EpisodesModel(
    val air_date: String,
    val characters: List<Any>,
    val created: String,
    val episode: String,
    val id: Int,
    val name: String,
    val url: String
)
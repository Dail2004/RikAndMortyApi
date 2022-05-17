package com.dail.reckandmortyapi.presentation.model.episode

import com.dail.reckandmortyapi.presentation.base.BaseDiffUtil
import com.example.domain.model.episode.EpisodesModel

data class EpisodesUI(
    val air_date: String,
    val characters: List<Any>,
    val created: String,
    val episode: String,
    override val id: Int,
    val name: String,
    val url: String
): BaseDiffUtil

fun EpisodesModel.toUI() = EpisodesUI(
    air_date, characters, created, episode, id, name, url
)
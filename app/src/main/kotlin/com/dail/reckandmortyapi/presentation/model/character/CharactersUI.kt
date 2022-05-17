package com.dail.reckandmortyapi.presentation.model.character

import com.dail.reckandmortyapi.presentation.base.BaseDiffUtil
import com.dail.reckandmortyapi.presentation.model.RickAndMortyModelUI
import com.example.domain.model.character.CharactersModel

data class CharactersUI(
    val created: String,
    val episode: List<String>,
    val gender: String,
    override val id: Int,
    val image: String,
    val location: LocationUI,
    val name: String,
    val origin: OriginUI,
    val species: String,
    val status: String,
    val type: String,
    val url: String,
    var firstSeenIn: String = ""
) : BaseDiffUtil

fun CharactersModel.toUI() = CharactersUI(
    created,
    episode,
    gender,
    id,
    image,
    location.toUI(),
    name,
    origin.toUI(),
    species,
    status,
    type,
    url
)


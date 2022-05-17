package com.dail.reckandmortyapi.presentation.model

import com.dail.reckandmortyapi.presentation.base.BaseDiffUtil
import com.dail.reckandmortyapi.presentation.model.character.CharactersUI
import com.dail.reckandmortyapi.presentation.model.episode.EpisodesUI
import com.dail.reckandmortyapi.presentation.model.location.LocationsUI


sealed class RickAndMortyModelUI : BaseDiffUtil {
    class CharacterUI(val charactersUI: CharactersUI) : RickAndMortyModelUI() {
        override val id: Int
            get() = TODO("Not yet implemented")

        override fun equals(other: Any?): Boolean {
            TODO("Not yet implemented")
        }
    }

    class EpisodeUI(val episodesUI: EpisodesUI) : RickAndMortyModelUI() {
        override val id: Int
            get() = TODO("Not yet implemented")

        override fun equals(other: Any?): Boolean {
            TODO("Not yet implemented")
        }
    }

    class LocationUI(val locationsUI: LocationsUI) : RickAndMortyModelUI() {
        override val id: Int
            get() = TODO("Not yet implemented")

        override fun equals(other: Any?): Boolean {
            TODO("Not yet implemented")
        }
    }
}

fun CharactersUI.toUI() = RickAndMortyModelUI.CharacterUI(
    CharactersUI(
        created,
        episode,
        gender,
        id,
        image,
        location,
        name,
        origin,
        species,
        status,
        type,
        url
    )
)

fun EpisodesUI.toUI() = RickAndMortyModelUI.EpisodeUI(
    EpisodesUI(air_date, characters, created, episode, id, name, url)
)

fun LocationsUI.toUI() = RickAndMortyModelUI.LocationUI(
    LocationsUI(created, dimension, id, name, residents, type, url)
)


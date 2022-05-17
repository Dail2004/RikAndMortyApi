package com.dail.reckandmortyapi.presentation.ui.fragment.search

sealed class RickAndMortyNames {
    data class GetAllCharactersByName(val characterName: String) : RickAndMortyNames()
    data class GetAllEpisodesByName(val episodeName: String) : RickAndMortyNames()
    data class GetAllLocationsByName(val locationName: String) : RickAndMortyNames()
}
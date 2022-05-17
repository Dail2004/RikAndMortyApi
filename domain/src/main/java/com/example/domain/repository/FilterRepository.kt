package com.example.domain.repository

import com.example.common.resource.Resource
import com.example.domain.model.character.CharactersModel
import com.example.domain.model.episode.EpisodesModel
import com.example.domain.model.location.LocationsModel
import kotlinx.coroutines.flow.Flow

interface FilterRepository {
    fun fetchCharacterFilter(name: String?): Flow<Resource<List<CharactersModel>>>
    fun fetchLLocationFilter(name: String?): Flow<Resource<List<LocationsModel>>>
    fun fetchEpisodeFilter(name: String?): Flow<Resource<List<EpisodesModel>>>
}
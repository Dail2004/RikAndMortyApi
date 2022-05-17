package com.example.data.repositories

import com.example.data.remote.apiservice.FilterApiService
import com.example.data.remote.dtos.character.toDomain
import com.example.data.remote.dtos.episode.toDomain
import com.example.data.remote.dtos.location.toDomain
import com.example.data.repositories.base.BaseRepository
import com.example.domain.repository.FilterRepository
import javax.inject.Inject

class FilterRepositoryImpl @Inject constructor(
    private val service: FilterApiService
) : BaseRepository(), FilterRepository {
    override fun fetchCharacterFilter(name: String?) = doRequest {
        service.fetchCharactersName(name).results.map { it.toDomain() }
    }

    override fun fetchLLocationFilter(name: String?) = doRequest {
        service.fetchLocationName(name).results.map { it.toDomain() }
    }

    override fun fetchEpisodeFilter(name: String?) = doRequest {
        service.fetchEpisodeName(name).results.map { it.toDomain() }
    }

}
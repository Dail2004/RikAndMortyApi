package com.example.data.repositories

import com.example.data.remote.apiservice.EpisodeApiService
import com.example.data.remote.dtos.episode.toDomain
import com.example.data.repositories.base.BaseRepository
import com.example.domain.repository.EpisodesRepository
import javax.inject.Inject

class EpisodesRepositoryImpl @Inject constructor(
    private val service: EpisodeApiService
) : BaseRepository(), EpisodesRepository {
    override fun fetchEpisode(page: Int, name: String, episode: String) = doRequest {
        service.fetchEpisodes(page, name, episode).results.map { it.toDomain() }
    }

}
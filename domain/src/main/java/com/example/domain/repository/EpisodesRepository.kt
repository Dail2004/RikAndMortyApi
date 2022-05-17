package com.example.domain.repository

import com.example.common.resource.Resource
import com.example.domain.model.episode.EpisodesModel
import kotlinx.coroutines.flow.Flow

interface EpisodesRepository {
    fun fetchEpisode(page: Int, name: String, episode: String) : Flow<Resource<List<EpisodesModel>>>
}
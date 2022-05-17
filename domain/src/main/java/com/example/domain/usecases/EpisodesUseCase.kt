package com.example.domain.usecases

import com.example.domain.repository.EpisodesRepository
import javax.inject.Inject

class EpisodesUseCase @Inject constructor(
    private val repository: EpisodesRepository
) {
    operator fun invoke(page: Int, name: String, episode: String) = repository.fetchEpisode(page, name, episode)
}
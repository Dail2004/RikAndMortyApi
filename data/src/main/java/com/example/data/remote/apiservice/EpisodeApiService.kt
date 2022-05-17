package com.example.data.remote.apiservice

import com.example.data.remote.dtos.ResponseDto
import com.example.data.remote.dtos.episode.EpisodeDto
import retrofit2.http.GET
import retrofit2.http.Query

interface EpisodeApiService {
    @GET("episode")
    suspend fun fetchEpisodes(
        @Query("page") page: Int,
        @Query("name") name: String,
        @Query("episode") episode: String
    ): ResponseDto<EpisodeDto>
}
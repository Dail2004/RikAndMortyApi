package com.example.data.remote.apiservice

import com.example.data.remote.dtos.ResponseDto
import com.example.data.remote.dtos.character.CharacterDto
import com.example.data.remote.dtos.episode.EpisodeDto
import com.example.data.remote.dtos.location.LocationDto
import retrofit2.http.GET
import retrofit2.http.Query

interface FilterApiService {
    @GET("character")
    suspend fun fetchCharactersName(
        @Query("name") characterName: String?
    ): ResponseDto<CharacterDto>

    @GET("location")
    suspend fun fetchLocationName(
        @Query("name") nameOfLocation: String?
    ): ResponseDto<LocationDto>

    @GET("episode")
    suspend fun fetchEpisodeName(
        @Query("name") nameOfEpisode: String?
    ): ResponseDto<EpisodeDto>
}
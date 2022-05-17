package com.example.data.remote.apiservice

import com.example.data.remote.dtos.ResponseDto
import com.example.data.remote.dtos.character.CharacterDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharactersApiService {

    @GET("character")
    suspend fun fetchCharacters(
        @Query("page") page: Int,
        @Query("name") name: String,
        @Query("status") status: String
    ): ResponseDto<CharacterDto>

    @GET("character/{id}")
    suspend fun fetchCharacter(
        @Path("id") id: Int,
    ): CharacterDto
}
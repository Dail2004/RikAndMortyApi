package com.example.data.remote.apiservice

import com.example.data.remote.dtos.ResponseDto
import com.example.data.remote.dtos.location.LocationDto
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationApiService {
    @GET("location")
    suspend fun fetchLocations(
        @Query("page") page: Int,
        @Query("name") name: String
    ): ResponseDto<LocationDto>
}
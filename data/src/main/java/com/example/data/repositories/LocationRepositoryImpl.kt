package com.example.data.repositories

import com.example.data.remote.apiservice.LocationApiService
import com.example.data.remote.dtos.location.toDomain
import com.example.data.repositories.base.BaseRepository
import com.example.domain.repository.LocationRepository
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val service: LocationApiService
) : BaseRepository(), LocationRepository {
    override fun fetchLocation(page: Int, name: String, type: String, dimension: String) =
        doRequest {
            service.fetchLocations(page, name, type, dimension).results.map { it.toDomain() }
        }
}
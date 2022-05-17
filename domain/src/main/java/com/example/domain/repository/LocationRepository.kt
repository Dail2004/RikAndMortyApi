package com.example.domain.repository

import com.example.common.resource.Resource
import com.example.domain.model.location.LocationsModel
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    fun fetchLocation(page: Int, name: String): Flow<Resource<List<LocationsModel>>>
}
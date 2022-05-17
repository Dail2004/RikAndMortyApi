package com.example.domain.usecases

import com.example.domain.repository.LocationRepository
import javax.inject.Inject

class LocationsUseCase @Inject constructor(
    private val repository: LocationRepository
) {
    operator fun invoke(page: Int, name: String) = repository.fetchLocation(page, name)
}
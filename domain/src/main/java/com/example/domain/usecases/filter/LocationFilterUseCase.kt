package com.example.domain.usecases.filter

import com.example.domain.repository.FilterRepository
import javax.inject.Inject

class LocationFilterUseCase @Inject constructor(
    private val repository: FilterRepository
) {
    operator fun invoke(name: String) = repository.fetchLLocationFilter(name)
}
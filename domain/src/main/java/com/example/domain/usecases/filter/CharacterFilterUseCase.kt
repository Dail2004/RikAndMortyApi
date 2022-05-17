package com.example.domain.usecases.filter

import com.example.domain.repository.FilterRepository
import javax.inject.Inject

class CharacterFilterUseCase @Inject constructor(
    private val repository: FilterRepository
) {
    operator fun invoke(name: String) = repository.fetchCharacterFilter(name)
}
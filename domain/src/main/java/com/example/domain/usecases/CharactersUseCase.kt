package com.example.domain.usecases

import com.example.domain.repository.CharactersRepository
import javax.inject.Inject

class CharactersUseCase @Inject constructor(
    private val repository: CharactersRepository
) {
    operator fun invoke(page:   Int, name: String) = repository.fetchCharacters(page, name)
}
package com.example.domain.usecases.detail

import com.example.domain.repository.CharactersRepository
import javax.inject.Inject

class CharacterUseCase @Inject constructor(
    private val repository: CharactersRepository
) {
    operator fun invoke(id: Int) = repository.fetchCharacter(id)
}
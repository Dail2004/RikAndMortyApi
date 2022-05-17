package com.example.domain.repository

import com.example.common.resource.Resource
import com.example.domain.model.character.CharactersModel
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {
    fun fetchCharacters(page: Int, name: String): Flow<Resource<List<CharactersModel>>>

    fun fetchCharacter(id: Int): Flow<Resource<CharactersModel>>
}

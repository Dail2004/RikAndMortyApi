package com.example.data.repositories

import com.example.data.remote.apiservice.CharactersApiService
import com.example.data.remote.dtos.character.toDomain
import com.example.data.repositories.base.BaseRepository
import com.example.domain.repository.CharactersRepository
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val service: CharactersApiService
) : BaseRepository(), CharactersRepository {

    override fun fetchCharacters(page: Int, name: String, status: String) = doRequest {
        service.fetchCharacters(page, name, status).results.map { it.toDomain() }
    }

    override fun fetchCharacter(id: Int) = doRequest {
        service.fetchCharacter(id).toDomain()
    }
}
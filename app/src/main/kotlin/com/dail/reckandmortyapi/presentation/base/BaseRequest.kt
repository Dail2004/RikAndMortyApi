package com.dail.reckandmortyapi.presentation.base

interface BaseRequest {
    var page: Int
    fun fetchCharacters(page: Int, name: String)
    fun fetchLocations(page: Int, name: String)
    fun fetchEpisodes(page: Int, name: String)
}
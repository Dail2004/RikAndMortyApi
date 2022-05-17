package com.dail.reckandmortyapi.presentation.base

interface BaseRequest {
    var page: Int
    fun fetchCharacters(page: Int, name: String, status: String)
    fun fetchLocations(page: Int, name: String, type: String, dimension:String)
    fun fetchEpisodes(page: Int, name: String, episode: String)
}
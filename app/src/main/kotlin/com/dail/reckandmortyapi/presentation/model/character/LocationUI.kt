package com.dail.reckandmortyapi.presentation.model.character

import com.example.domain.model.character.LocationModel

data class LocationUI(
    val name: String,
    val url: String
)

fun LocationModel.toUI() = LocationUI(
    name, url
)
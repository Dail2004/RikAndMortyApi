package com.dail.reckandmortyapi.presentation.model.character

import com.example.domain.model.character.OriginModel

data class OriginUI(
    val name: String,
    val url: String
)

fun OriginModel.toUI() = OriginUI(
    name, url
)
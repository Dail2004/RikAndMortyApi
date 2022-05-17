package com.dail.reckandmortyapi.presentation.model.location

import com.dail.reckandmortyapi.presentation.base.BaseDiffUtil
import com.example.domain.model.location.LocationsModel

data class LocationsUI(
    val created: String,
    val dimension: String,
    override val id: Int,
    val name: String,
    val residents: List<Any>,
    val type: String,
    val url: String
): BaseDiffUtil

fun LocationsModel.toUI() = LocationsUI(
    created, dimension, id, name, residents, type, url
)
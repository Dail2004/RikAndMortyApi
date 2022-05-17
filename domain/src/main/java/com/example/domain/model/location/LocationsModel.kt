package com.example.domain.model.location

data class LocationsModel(
    val created: String,
    val dimension: String,
    val id: Int,
    val name: String,
    val residents: List<Any>,
    val type: String,
    val url: String
)
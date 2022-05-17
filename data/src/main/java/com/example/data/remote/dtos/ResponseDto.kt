package com.example.data.remote.dtos

import com.google.gson.annotations.SerializedName

data class ResponseDto<T>(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val results: List<T>
)
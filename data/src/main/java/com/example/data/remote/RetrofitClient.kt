package com.example.data.remote

import com.example.data.remote.apiservice.CharactersApiService
import com.example.data.remote.apiservice.EpisodeApiService
import com.example.data.remote.apiservice.FilterApiService
import com.example.data.remote.apiservice.LocationApiService
import com.example.data.remote.interceptors.LoggingInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {
    private val okHttpClient: OkHttpClient = OkHttpClient()
        .newBuilder()
        .addInterceptor(LoggingInterceptor().provideLoggingInterceptor())
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    private val provideRetrofit = Retrofit.Builder()
        .baseUrl("https://rickandmortyapi.com/api/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun provideCharactersApiService(): CharactersApiService = provideRetrofit
        .create(CharactersApiService::class.java)

    fun provideLocationsApiService(): LocationApiService = provideRetrofit
        .create(LocationApiService::class.java)

    fun provideEpisodesApiService(): EpisodeApiService = provideRetrofit
        .create(EpisodeApiService::class.java)

    fun provideFiltersApiService(): FilterApiService = provideRetrofit
        .create(FilterApiService::class.java)
}
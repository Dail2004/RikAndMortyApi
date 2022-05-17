package com.dail.reckandmortyapi.di

import com.example.data.remote.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    val retrofitClient = RetrofitClient()

    @Singleton
    @Provides
    fun provideCharactersApiService() = retrofitClient.provideCharactersApiService()

    @Singleton
    @Provides
    fun provideLocationsApiService() = retrofitClient.provideLocationsApiService()

    @Singleton
    @Provides
    fun provideEpisodesApiService() = retrofitClient.provideEpisodesApiService()

    @Singleton
    @Provides
    fun provideFiltersApiService() = retrofitClient.provideFiltersApiService()
}
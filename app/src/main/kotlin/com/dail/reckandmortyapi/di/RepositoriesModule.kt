package com.dail.reckandmortyapi.di

import com.example.data.repositories.CharactersRepositoryImpl
import com.example.data.repositories.EpisodesRepositoryImpl
import com.example.data.repositories.FilterRepositoryImpl
import com.example.data.repositories.LocationRepositoryImpl
import com.example.domain.repository.CharactersRepository
import com.example.domain.repository.EpisodesRepository
import com.example.domain.repository.FilterRepository
import com.example.domain.repository.LocationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoriesModule {

    @Binds
    abstract fun bindCharactersRepository(repositoryImpl: CharactersRepositoryImpl): CharactersRepository

    @Binds
    abstract fun bindLocationsRepository(repositoryImpl: LocationRepositoryImpl): LocationRepository

    @Binds
    abstract fun bindEpisodesRepository(repositoryImpl: EpisodesRepositoryImpl): EpisodesRepository

    @Binds
    abstract fun bindFiltersRepository(filterImpl: FilterRepositoryImpl): FilterRepository

}
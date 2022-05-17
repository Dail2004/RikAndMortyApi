package com.dail.reckandmortyapi.presentation.ui.fragment.search

import androidx.lifecycle.viewModelScope
import com.dail.reckandmortyapi.presentation.base.BaseRequest
import com.dail.reckandmortyapi.presentation.base.BaseViewModel
import com.dail.reckandmortyapi.presentation.model.RickAndMortyModelUI
import com.dail.reckandmortyapi.presentation.model.character.toUI
import com.dail.reckandmortyapi.presentation.model.episode.toUI
import com.dail.reckandmortyapi.presentation.model.location.toUI
import com.dail.reckandmortyapi.presentation.model.toUI
import com.dail.reckandmortyapi.presentation.state.UIState
import com.example.domain.usecases.filter.CharacterFilterUseCase
import com.example.domain.usecases.filter.EpisodeFilterUseCase
import com.example.domain.usecases.filter.LocationFilterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val characterUseCase: CharacterFilterUseCase,
    private val episodeCase: EpisodeFilterUseCase,
    private val locationUseCase: LocationFilterUseCase
) : BaseViewModel(), BaseRequest {

    private val _charactersState =
        MutableStateFlow<UIState<List<RickAndMortyModelUI.CharacterUI>>>(UIState.Loading())
    var charactersState: StateFlow<UIState<List<RickAndMortyModelUI.CharacterUI>>> =
        _charactersState.asStateFlow()

    private val _episodesState =
        MutableStateFlow<UIState<List<RickAndMortyModelUI.EpisodeUI>>>(UIState.Loading())
    val episodesState: StateFlow<UIState<List<RickAndMortyModelUI.EpisodeUI>>> = _episodesState

    private val _locationsState =
        MutableStateFlow<UIState<List<RickAndMortyModelUI.LocationUI>>>(UIState.Loading())
    var locationsState: StateFlow<UIState<List<RickAndMortyModelUI.LocationUI>>> =
        _locationsState.asStateFlow()

    private val _searchQuery: MutableStateFlow<String> =
        MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()
    private var searchJob: Job? = null

    companion object {
        const val TIME_MILLIS = 500L
    }

    fun onEvent(event: RickAndMortyNames) {
        when (event) {
            is RickAndMortyNames.GetAllCharactersByName -> onSearch(event.characterName)
            is RickAndMortyNames.GetAllEpisodesByName -> onSearch(event.episodeName)
            is RickAndMortyNames.GetAllLocationsByName -> onSearch(event.locationName)
        }
    }

    override var page = 1

    override fun fetchCharacters(page: Int, name: String) {
        characterUseCase(
            name
        ).collectRequest(_charactersState) { it.map { data -> data.toUI().toUI() } }
    }

    override fun fetchEpisodes(page: Int, name: String) {
        episodeCase(name).collectRequest(_episodesState) {
            it.map { data ->
                data.toUI().toUI()
            }
        }
    }

    override fun fetchLocations(page: Int, name: String) {
        locationUseCase(name).collectRequest(_locationsState) {
            it.map { data ->
                data.toUI().toUI()
            }
        }
    }

    private fun onSearch(query: String) {
        _searchQuery.value = query
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(TIME_MILLIS)
            fetchCharacters(page, query)
        }
        searchJob = viewModelScope.launch {
            delay(TIME_MILLIS)
            fetchEpisodes(page, query)
        }
        searchJob = viewModelScope.launch {
            delay(TIME_MILLIS)
            fetchLocations(page, query)
        }
    }
}

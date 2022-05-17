package com.dail.reckandmortyapi.presentation.ui.fragment.episodes

import androidx.lifecycle.viewModelScope
import com.dail.reckandmortyapi.presentation.base.BaseRequest
import com.dail.reckandmortyapi.presentation.base.BaseViewModel
import com.dail.reckandmortyapi.presentation.model.episode.EpisodesUI
import com.dail.reckandmortyapi.presentation.model.episode.toUI
import com.dail.reckandmortyapi.presentation.state.UIState
import com.dail.reckandmortyapi.presentation.ui.fragment.search.RickAndMortyNames
import com.example.domain.usecases.EpisodesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodesViewModel @Inject constructor(
    private val useCase: EpisodesUseCase
) : BaseViewModel(), BaseRequest {

    private val _episodesState = MutableStateFlow<UIState<List<EpisodesUI>>>(UIState.Loading())
    val episodesState: StateFlow<UIState<List<EpisodesUI>>> = _episodesState

    private val _searchQuery: MutableStateFlow<String> =
        MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()
    private var searchJob: Job? = null
    override var page = 1

    companion object {
        const val TIME_MILLIS = 500L
    }

    fun onEvent(event: RickAndMortyNames) {
        when (event) {
            is RickAndMortyNames.GetAllEpisodesByName -> onSearch(event.episodeName)
        }
    }

    private fun onSearch(query: String) {
        _searchQuery.value = query
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(TIME_MILLIS)
            fetchEpisodes(page, query)
        }
    }

    init {
        fetchLocations(page, "")
        fetchEpisodes(page, "")
    }

    override fun fetchCharacters(page: Int, name: String) {

    }

    override fun fetchEpisodes(page: Int, name: String) {
        useCase(page, name).collectRequest(_episodesState) { it.map { data -> data.toUI() } }
    }

    override fun fetchLocations(page: Int, name: String) {
    }
}
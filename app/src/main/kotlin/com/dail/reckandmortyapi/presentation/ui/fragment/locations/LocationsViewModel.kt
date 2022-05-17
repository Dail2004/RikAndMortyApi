package com.dail.reckandmortyapi.presentation.ui.fragment.locations

import androidx.lifecycle.viewModelScope
import com.dail.reckandmortyapi.presentation.base.BaseRequest
import com.dail.reckandmortyapi.presentation.base.BaseViewModel
import com.dail.reckandmortyapi.presentation.model.location.LocationsUI
import com.dail.reckandmortyapi.presentation.model.location.toUI
import com.dail.reckandmortyapi.presentation.state.UIState
import com.dail.reckandmortyapi.presentation.ui.fragment.search.RickAndMortyNames
import com.example.domain.usecases.LocationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationsViewModel @Inject constructor(
    private val useCase: LocationsUseCase
) : BaseViewModel(), BaseRequest {

    private val _locationsState =
        MutableStateFlow<UIState<List<LocationsUI>>>(UIState.Loading())
    var locationsState: StateFlow<UIState<List<LocationsUI>>> = _locationsState.asStateFlow()

    private val _searchQuery: MutableStateFlow<String> =
        MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()
    private var searchJob: Job? = null
    override var page = 1
    override fun fetchCharacters(page: Int, name: String, status: String) {

    }

    companion object {
        const val TIME_MILLIS = 500L
    }

    fun onEvent(event: RickAndMortyNames) {
        when (event) {
            is RickAndMortyNames.GetAllLocationsByName -> onSearch(event.locationName)
        }
    }

    private fun onSearch(query: String) {
        _searchQuery.value = query
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(TIME_MILLIS)
            fetchLocations(page, query, "", "")
        }
    }

    init {
        fetchLocations(page, "", "", "")
    }

    override fun fetchLocations(page: Int, name: String, type: String, dimension: String) {
        useCase(
            page,
            name,
            type,
            dimension
        ).collectRequest(_locationsState) { it.map { data -> data.toUI() } }
    }

    override fun fetchEpisodes(page: Int, name: String, episode: String) {
    }
}
package com.dail.reckandmortyapi.presentation.ui.fragment.characters

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.dail.reckandmortyapi.presentation.base.BaseRequest
import com.dail.reckandmortyapi.presentation.base.BaseViewModel
import com.dail.reckandmortyapi.presentation.model.character.CharactersUI
import com.dail.reckandmortyapi.presentation.model.character.toUI
import com.dail.reckandmortyapi.presentation.state.UIState
import com.dail.reckandmortyapi.presentation.ui.fragment.search.RickAndMortyNames
import com.example.domain.usecases.CharactersUseCase
import com.example.domain.usecases.detail.CharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val fetchCharactersUseCase: CharactersUseCase,
    private val fetchCharacterUseCase: CharacterUseCase
) : BaseViewModel(), BaseRequest {

    override var page = 1

    private val _charactersState =
        MutableStateFlow<UIState<List<CharactersUI>>>(UIState.Loading())
    var charactersState: StateFlow<UIState<List<CharactersUI>>> = _charactersState.asStateFlow()

    private val _characterState =
        MutableStateFlow<UIState<CharactersUI>>(UIState.Loading())
    var characterState: StateFlow<UIState<CharactersUI>> = _characterState.asStateFlow()

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
        }
    }

    init {
        fetchCharacters(page, "", "")
    }

    override fun fetchCharacters(page: Int, name: String, status: String) {
        fetchCharactersUseCase(
            page,
            name,
            status
        ).collectRequests(_charactersState) { it.map { data -> data.toUI() } }
    }

    override fun fetchLocations(page: Int, name: String, type: String, dimension: String) {
    }

    override fun fetchEpisodes(page: Int, name: String, episode: String) {
    }

    fun fetchCharacter(id: Int) {
        fetchCharacterUseCase(id).collectRequest(_characterState) { it.toUI() }
    }

    private fun onSearch(query: String) {
        _searchQuery.value = query
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(TIME_MILLIS)
            fetchCharacters(page, query, "")
            Log.d("fuck", "onSearch: ${fetchCharacters(page, query, "")}")
        }
    }
}
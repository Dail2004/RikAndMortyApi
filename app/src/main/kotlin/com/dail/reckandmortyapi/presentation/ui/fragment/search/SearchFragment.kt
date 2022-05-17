package com.dail.reckandmortyapi.presentation.ui.fragment.search

import android.annotation.SuppressLint
import android.util.Log
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.dail.reckandmortyapi.presentation.base.BaseFragment
import com.dail.reckandmortyapi.presentation.base.extension.scrollListenNextPage
import com.dail.reckandmortyapi.presentation.base.extension.searchItem
import com.dail.reckandmortyapi.presentation.model.RickAndMortyModelUI
import com.dail.reckandmortyapi.presentation.state.UIState
import com.dail.reckandmortyapi.presentation.ui.adapter.RickAndMortyAdapter
import com.example.rikandmortyapi.R
import com.example.rikandmortyapi.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment :
    BaseFragment<SearchViewModel, FragmentSearchBinding>(R.layout.fragment_search) {
    override val viewModel: SearchViewModel by viewModels()
    override val binding by viewBinding(FragmentSearchBinding::bind)
    private val list = arrayListOf<RickAndMortyModelUI>()
    private val adapter = RickAndMortyAdapter(
        list,
        this::onItemLongClick,
        this::fetchFirstSeenIn,
        this::onItemClick,
        this::locationItemClick,
        this::episodeItemClick
    )

    private fun fetchFilter(s: String) {
        viewModel.fetchLocations(1, s, "", "")
        viewModel.fetchCharacters(1, s, "")
        viewModel.fetchEpisodes(1, s, "")
    }

    override fun setupRequests() {
        binding.searchRecycler.setHasFixedSize(true)
        binding.searchRecycler.adapter = adapter
        binding.searchRecycler.scrollListenNextPage(viewModel)
        fetchFilter("")
    }

    override fun setupSubscribes() {
        fetchCharacters()
        fetchLocations()
        fetchEpisodes()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun fetchCharacters() {
        viewModel.charactersState.collectUIState {
            when (it) {
                is UIState.Error -> {}
                is UIState.Loading -> {}
                is UIState.Success -> it.data.let { data ->
                    val sortedList = data.sortedByDescending { list ->
                        list.charactersUI.created
                    }
                    list.addAll(sortedList)
                }
            }
            adapter.notifyDataSetChanged()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun fetchLocations() {
        viewModel.locationsState.collectUIState {
            when (it) {
                is UIState.Error -> {}
                is UIState.Loading -> {}
                is UIState.Success -> it.data.let { data ->
                    val sortedList = data.sortedByDescending { list ->
                        list.locationsUI.created
                    }
                    list.addAll(sortedList)
                }
            }
            adapter.notifyDataSetChanged()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun fetchEpisodes() {
        viewModel.episodesState.collectUIState {
            when (it) {
                is UIState.Error -> {}
                is UIState.Loading -> {
                }
                is UIState.Success -> it.data.let { data ->
                    val sortedList = data.sortedByDescending { list ->
                        list.episodesUI.created
                    }
                    list.addAll(sortedList)
                }
            }
            adapter.notifyDataSetChanged()
        }
    }

    override fun setupListeners() {
        searchItems()
    }

    private fun searchItems() {
        binding.searchAllParameters.searchItem {
            fetchFilter(it.toString())
            list.clear()
        }
    }

    private fun onItemLongClick(image: String) {

    }

    private fun onItemClick(name: String, id: Int) {
        findNavController().navigate(
            SearchFragmentDirections.actionSearchFragmentToCharacterDetailFragment(
                "Characters: $name",
                id
            )
        )
        Log.d("fuck", "onItemClickListener: $id")
    }

    private fun fetchFirstSeenIn(position: Int, episodeUrl: String) {

    }

    private fun locationItemClick(name: String, id: Int) {

    }

    private fun episodeItemClick(name: String, id: Int) {

    }
}
package com.dail.reckandmortyapi.presentation.ui.fragment.episodes

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.widget.Button
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.dail.reckandmortyapi.presentation.base.BaseFragment
import com.dail.reckandmortyapi.presentation.base.extension.scrollListenNextPage
import com.dail.reckandmortyapi.presentation.model.episode.EpisodesUI
import com.dail.reckandmortyapi.presentation.state.UIState
import com.dail.reckandmortyapi.presentation.ui.adapter.EpisodeAdapter
import com.dail.reckandmortyapi.presentation.ui.fragment.search.RickAndMortyNames
import com.example.rikandmortyapi.R
import com.example.rikandmortyapi.databinding.FragmentEpisodesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EpisodesFragment :
    BaseFragment<EpisodesViewModel, FragmentEpisodesBinding>(R.layout.fragment_episodes) {
    override val viewModel: EpisodesViewModel by viewModels()
    override val binding by viewBinding(FragmentEpisodesBinding::bind)
    private val adapter = EpisodeAdapter(this::onItemClick)
    private val episodes = ArrayList<EpisodesUI>(adapter.currentList)
    private val args: EpisodesFragmentArgs by navArgs()

    private fun onItemClick(name: String, id: Int) {
        findNavController().navigate(
            EpisodesFragmentDirections.actionEpisodesFragmentToEpisodeDetailFragment(
                "Episode: $name",
                id
            )
        )
        Log.d("fuck", "onItemClickListener: $id")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)

        val search = menu.findItem(R.id.search)
        val searchView = search.actionView as? SearchView

        val filter = menu.findItem(R.id.filter)
        val filterView = filter.actionView as Button

        filterView.setOnClickListener {
            findNavController().navigate(R.id.dialogFilterEpisodeFragment)
        }

        searchView?.isSubmitButtonEnabled = true

        searchView?.queryHint = "search..."
        searchView?.setQuery(viewModel.searchQuery.value, true)
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                performSearchEvent(query)
                return false
            }
        })
    }

    private fun performSearchEvent(name: String) {
        viewModel.onEvent(RickAndMortyNames.GetAllEpisodesByName(name))
        episodes.clear()
    }

    override fun setupRequests() {
        binding.episodesRecycler.adapter = adapter
        binding.episodesRecycler.scrollListenNextPage(viewModel)
        argsNav()
    }

    private fun argsNav() {
        viewModel.fetchEpisodes(1, args.name.toString(), args.episode.toString())
        episodes.clear()
    }

    override fun setupSubscribes() {
        viewModel.episodesState.collectUIState {
            when (it) {
                is UIState.Error -> {
                    Log.d("anime", "setupSubscribes: ${it.error}")
                }
                is UIState.Loading -> {
                }

                is UIState.Success -> {
                    Log.d("anime", "setupSubscribes: ${it.data}")
                    episodes.addAll(it.data)
                    adapter.submitList(episodes)
                }
            }
        }
    }
}
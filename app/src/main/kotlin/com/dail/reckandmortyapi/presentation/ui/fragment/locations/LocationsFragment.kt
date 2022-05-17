package com.dail.reckandmortyapi.presentation.ui.fragment.locations

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
import com.dail.reckandmortyapi.presentation.model.location.LocationsUI
import com.dail.reckandmortyapi.presentation.state.UIState
import com.dail.reckandmortyapi.presentation.ui.adapter.LocationAdapter
import com.dail.reckandmortyapi.presentation.ui.fragment.search.RickAndMortyNames
import com.example.rikandmortyapi.R
import com.example.rikandmortyapi.databinding.FragmentLocationsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocationsFragment :
    BaseFragment<LocationsViewModel, FragmentLocationsBinding>(R.layout.fragment_locations) {
    override val viewModel: LocationsViewModel by viewModels()
    override val binding by viewBinding(FragmentLocationsBinding::bind)
    private val adapter = LocationAdapter(
        this::onItemClick
    )
    private val locations = ArrayList<LocationsUI>(adapter.currentList)
    private val args: LocationsFragmentArgs by navArgs()

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
            findNavController().navigate(R.id.dialogFilterLocationFragment)
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
        viewModel.onEvent(RickAndMortyNames.GetAllLocationsByName(name))
        locations.clear()
    }

    override fun setupRequests() {
        binding.locationRecycler.adapter = adapter
        binding.locationRecycler.scrollListenNextPage(viewModel)
        argsNav()
    }

    private fun argsNav() {
        viewModel.fetchLocations(1, "", args.dimension.toString(), args.type.toString())
        locations.clear()
    }

    override fun setupSubscribes() {
        viewModel.locationsState.collectUIState {
            when (it) {
                is UIState.Error -> {
                    Log.d("anime", "setupSubscribes: ${it.error}")
                }
                is UIState.Loading -> {
                }

                is UIState.Success -> {
                    Log.d("anime", "setupSubscribes: ${it.data}")
                    locations.addAll(it.data)
                    adapter.submitList(locations)
                }
            }
        }
    }

    private fun onItemClick(name: String, id: Int) {
        findNavController().navigate(
            LocationsFragmentDirections.actionLocationsFragmentToLocationDetailFragment(
                "Location: $name",
                id
            )
        )
    }
}
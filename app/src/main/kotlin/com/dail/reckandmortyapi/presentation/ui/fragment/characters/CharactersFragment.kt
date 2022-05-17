package com.dail.reckandmortyapi.presentation.ui.fragment.characters

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.dail.reckandmortyapi.presentation.base.BaseFragment
import com.dail.reckandmortyapi.presentation.base.extension.scrollListenNextPage
import com.dail.reckandmortyapi.presentation.model.character.CharactersUI
import com.dail.reckandmortyapi.presentation.state.UIState
import com.dail.reckandmortyapi.presentation.ui.adapter.CharacterAdapter
import com.dail.reckandmortyapi.presentation.ui.fragment.search.RickAndMortyNames
import com.example.rikandmortyapi.R
import com.example.rikandmortyapi.databinding.FragmentCharactersBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersFragment(
) :
    BaseFragment<CharactersViewModel, FragmentCharactersBinding>(R.layout.fragment_characters) {
    override val viewModel: CharactersViewModel by viewModels()
    override val binding by viewBinding(FragmentCharactersBinding::bind)
    private val adapter = CharacterAdapter(
        this::onItemClick,
        this::fetchFirstSeenIn,
        this::onItemLongClick,
    )
    private val characters = ArrayList<CharactersUI>(adapter.currentList)

    private fun onItemLongClick(image: String) {

    }

    private fun onItemClick(name: String, id: Int) {
        findNavController().navigate(
            CharactersFragmentDirections.actionCharactersFragmentToCharacterDetailFragment(
                "Character: $name",
                id
            )
        )
        Log.d("fuck", "onItemClickListener: $id")
    }

    private fun fetchFirstSeenIn(position: Int, episodeUrl: String) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)

        val search = menu.findItem(R.id.search)
        val searchView = search.actionView as? SearchView

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

    private fun performSearchEvent(characterName: String) {
        viewModel.onEvent(RickAndMortyNames.GetAllCharactersByName(characterName))
        characters.clear()
    }

    override fun setupRequests() {
        binding.charactersRecycler.adapter = adapter
        binding.charactersRecycler.scrollListenNextPage(viewModel)
        binding.charactersRecycler.setHasFixedSize(true)
    }

    override fun setupSubscribes() {
        viewModel.charactersState.collectUIState {
            when (it) {
                is UIState.Error -> {
                    Log.d("anime", "setupSubscribes: ${it.error}")
                }
                is UIState.Loading -> {}
                is UIState.Success -> {
                    characters.addAll(it.data)
                    adapter.submitList(characters)
                    Log.d("anime", "setupSubscribes: }")
                }
            }
        }
    }
}
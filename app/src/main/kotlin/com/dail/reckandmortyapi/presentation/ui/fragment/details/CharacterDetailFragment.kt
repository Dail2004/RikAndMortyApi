package com.dail.reckandmortyapi.presentation.ui.fragment.details

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.dail.reckandmortyapi.presentation.base.BaseFragment
import com.dail.reckandmortyapi.presentation.state.UIState
import com.dail.reckandmortyapi.presentation.ui.fragment.characters.CharactersViewModel
import com.example.rikandmortyapi.R
import com.example.rikandmortyapi.databinding.FragmentCharacterDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment :
    BaseFragment<CharactersViewModel, FragmentCharacterDetailBinding>(R.layout.fragment_character_detail) {
    override val viewModel: CharactersViewModel by viewModels()
    override val binding by viewBinding(FragmentCharacterDetailBinding::bind)
    private val args: CharacterDetailFragmentArgs by navArgs()

    override fun setupRequests() {
        viewModel.fetchCharacter(args.id)
        Log.d("fuck", "setupRequests: ${args}")
        viewModel.characterState.collectUIState {
            when (it) {
                is UIState.Error -> {}
                is UIState.Loading -> {}
                is UIState.Success -> {
                    it.data.let { data ->
                        binding.tvCharacterName.text = data.name
                        binding.tvCharacterSpecies.text = data.species
                        binding.tvCharacterGender.text = data.gender
                        binding.tvCharacterStatus.text = data.status
                        binding.ivCharacterProfile.load(data.image)
                        binding.tvCharacterOriginLocation.text = data.location.name
                        binding.tvCharacterLastLocation.text = data.location.url
                    }
                }
            }
        }
    }
}
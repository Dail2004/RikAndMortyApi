package com.dail.reckandmortyapi.presentation.ui.adapter.viewholder

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import coil.load
import com.dail.reckandmortyapi.presentation.model.RickAndMortyModelUI
import com.example.rikandmortyapi.R
import com.example.rikandmortyapi.databinding.CharactersItemBinding
import com.example.rikandmortyapi.databinding.EpisodesItemBinding
import com.example.rikandmortyapi.databinding.LocationItemBinding

sealed class SearchViewHolderAdapter<out V : ViewBinding>(
    binding: V
) : RecyclerView.ViewHolder(binding.root) {

    class CharacterViewHolder(
        val binding: CharactersItemBinding,
        val onItemLongClick: (photo: String) -> Unit,
        val onItemClickListener: (name: String, id: Int) -> Unit,
        val fetchFirstSeenIn: (position: Int, episodeUrl: String) -> Unit,
    ) : SearchViewHolderAdapter<CharactersItemBinding>(binding) {
        init {
            onItemClickListener
            itemView.setOnClickListener {
            }
        }

        fun onBind(item: RickAndMortyModelUI.CharacterUI) = with(binding) {
            image.load(item.charactersUI.image)
            name.text = item.charactersUI.name
            when (item.charactersUI.status) {
                "Alive" -> statusColor.setImageResource(R.drawable.status_alive)
                "Dead" -> statusColor.setImageResource(R.drawable.status_dead)
                "unknown" -> statusColor.setImageResource(R.drawable.status_unknown)
            }
            alive.text = item.charactersUI.status
            species.text = item.charactersUI.species
            with(location) {
                text = item.charactersUI.location.name
                isEnabled = item.charactersUI.location.url.isNotEmpty()
            }

            location.text = item.charactersUI.location.name
            location2.text = item.charactersUI.location.url
            setupFirstSeenIn(item.charactersUI.firstSeenIn, item.charactersUI.episode.first())
        }

        private fun setupFirstSeenIn(firstSeenIn: String? = null, episode: String) = with(binding) {
            location2.isVisible = firstSeenIn != null
            if (firstSeenIn == null) {
                fetchFirstSeenIn(absoluteAdapterPosition, episode)
            } else {
                location2.text = firstSeenIn
            }
        }
    }

    class EpisodeViewHolder(val binding: EpisodesItemBinding) :
        SearchViewHolderAdapter<EpisodesItemBinding>(binding) {
        fun onBind(item: RickAndMortyModelUI.EpisodeUI) = with(binding) {
            name.text = item.episodesUI.name
            airDate.text = item.episodesUI.air_date
            episode.text = item.episodesUI.episode
        }
    }

    class LocationViewHolder(val binding: LocationItemBinding) :
        SearchViewHolderAdapter<LocationItemBinding>(binding) {
        fun onBind(item: RickAndMortyModelUI.LocationUI) = with(binding) {
            name.text = item.locationsUI.name
            type.text = item.locationsUI.type
            dimension.text = item.locationsUI.dimension
        }
    }
}
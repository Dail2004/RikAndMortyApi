package com.dail.reckandmortyapi.presentation.ui.adapter.viewholder

import androidx.core.view.isVisible
import coil.load
import com.dail.reckandmortyapi.presentation.base.BaseViewHolder
import com.dail.reckandmortyapi.presentation.model.character.CharactersUI
import com.example.rikandmortyapi.R
import com.example.rikandmortyapi.databinding.CharactersItemBinding

class CharacterViewHolder(
    binding: CharactersItemBinding,
    val onItemLongClick: (photo: String) -> Unit,
    val onItemClickListener: (item: CharactersUI) -> Unit,
    val fetchFirstSeenIn: (position: Int, episodeUrl: String) -> Unit,
) : BaseViewHolder<CharactersItemBinding, CharactersUI>(binding) {

    override fun onBind(item: CharactersUI): Unit = with(binding) {
        image.load(item.image)
        name.text = item.name
        when (item.status) {
            "Alive" -> statusColor.setImageResource(R.drawable.status_alive)
            "Dead" -> statusColor.setImageResource(R.drawable.status_dead)
            "unknown" -> statusColor.setImageResource(R.drawable.status_unknown)
        }
        alive.text = item.status
        species.text = item.species
        with(location) {
            text = item.location.name
            isEnabled = item.location.url.isNotEmpty()
        }

        location.text = item.location.name
        location2.text = item.location.url
        setupFirstSeenIn(item.firstSeenIn, item.episode.first())
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
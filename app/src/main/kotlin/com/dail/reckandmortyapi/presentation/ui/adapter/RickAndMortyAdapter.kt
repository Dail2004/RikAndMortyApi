package com.dail.reckandmortyapi.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.dail.reckandmortyapi.presentation.model.RickAndMortyModelUI
import com.dail.reckandmortyapi.presentation.ui.adapter.viewholder.SearchViewHolderAdapter
import com.example.rikandmortyapi.R
import com.example.rikandmortyapi.databinding.CharactersItemBinding
import com.example.rikandmortyapi.databinding.EpisodesItemBinding
import com.example.rikandmortyapi.databinding.LocationItemBinding

class RickAndMortyAdapter(
    private var list: ArrayList<RickAndMortyModelUI>,
    private val onItemLongClick: (photo: String) -> Unit,
    private val fetchFirstSeenIn: (position: Int, episodeUrl: String) -> Unit,
    private var characterItemClick: (name: String, id: Int) -> Unit,
    private var locationItemClick: (name: String, id: Int) -> Unit,
    private var episodeItemClick: (name: String, id: Int) -> Unit
) : RecyclerView.Adapter<SearchViewHolderAdapter<ViewBinding>>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchViewHolderAdapter<ViewBinding> {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            R.layout.characters_item -> SearchViewHolderAdapter.CharacterViewHolder(
                CharactersItemBinding.inflate(inflater, parent, false),
                onItemLongClick,
                characterItemClick,
                fetchFirstSeenIn
            )

            R.layout.episodes_item -> SearchViewHolderAdapter.EpisodeViewHolder(
                EpisodesItemBinding.inflate(inflater, parent, false)
            )

            R.layout.location_item -> SearchViewHolderAdapter.LocationViewHolder(
                LocationItemBinding.inflate(inflater, parent, false)
            )
            else -> throw IllegalAccessException("Wrong view type provide")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (list[position]) {
            is RickAndMortyModelUI.CharacterUI -> R.layout.characters_item
            is RickAndMortyModelUI.EpisodeUI -> R.layout.episodes_item
            is RickAndMortyModelUI.LocationUI -> R.layout.location_item
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: SearchViewHolderAdapter<ViewBinding>, position: Int) {
        when (holder) {
            is SearchViewHolderAdapter.CharacterViewHolder -> {
                holder.onBind(list[position] as RickAndMortyModelUI.CharacterUI)
            }

            is SearchViewHolderAdapter.EpisodeViewHolder -> {
                holder.onBind(list[position] as RickAndMortyModelUI.EpisodeUI)
            }

            is SearchViewHolderAdapter.LocationViewHolder -> {
                holder.onBind(list[position] as RickAndMortyModelUI.LocationUI)
            }
        }
    }
}
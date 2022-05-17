package com.dail.reckandmortyapi.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dail.reckandmortyapi.presentation.base.BaseComparator
import com.dail.reckandmortyapi.presentation.model.episode.EpisodesUI
import com.example.rikandmortyapi.databinding.EpisodesItemBinding

class EpisodeAdapter(
    val onItemClick: (name: String, id: Int) -> Unit
) : ListAdapter<EpisodesUI, EpisodeAdapter.ViewHolder>(BaseComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            EpisodesItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    inner class ViewHolder(private val binding: EpisodesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {

        }

        fun onBind(item: EpisodesUI) = with(binding) {
            name.text = item.name
            airDate.text = item.air_date
            episode.text = item.episode
        }
    }
}
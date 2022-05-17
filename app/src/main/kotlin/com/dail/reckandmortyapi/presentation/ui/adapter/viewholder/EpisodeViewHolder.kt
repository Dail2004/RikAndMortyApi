package com.dail.reckandmortyapi.presentation.ui.adapter.viewholder

import com.dail.reckandmortyapi.presentation.base.BaseViewHolder
import com.dail.reckandmortyapi.presentation.model.episode.EpisodesUI
import com.example.rikandmortyapi.databinding.EpisodesItemBinding

class EpisodeViewHolder(binding: EpisodesItemBinding) :
    BaseViewHolder<EpisodesItemBinding, EpisodesUI>(binding) {
    override fun onBind(item: EpisodesUI) = with(binding) {
        name.text = item.name
        airDate.text = item.air_date
        episode.text = item.episode
    }
}
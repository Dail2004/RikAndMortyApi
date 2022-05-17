package com.dail.reckandmortyapi.presentation.ui.adapter.viewholder

import com.dail.reckandmortyapi.presentation.base.BaseViewHolder
import com.dail.reckandmortyapi.presentation.model.location.LocationsUI
import com.example.rikandmortyapi.databinding.LocationItemBinding

class LocationViewHolder(binding: LocationItemBinding) :
    BaseViewHolder<LocationItemBinding, LocationsUI>(binding) {
    override fun onBind(item: LocationsUI) = with(binding) {
        name.text = item.name
        type.text = item.type
        dimension.text = item.dimension
    }
}
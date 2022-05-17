package com.dail.reckandmortyapi.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dail.reckandmortyapi.presentation.base.BaseComparator
import com.dail.reckandmortyapi.presentation.model.location.LocationsUI
import com.example.rikandmortyapi.databinding.LocationItemBinding

class LocationAdapter(
    val onItemClick: (name: String, id: Int) -> Unit
) : ListAdapter<LocationsUI, LocationAdapter.ViewHolder>(BaseComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LocationItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    inner class ViewHolder(private val binding: LocationItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                getItem(absoluteAdapterPosition)?.apply {
                    onItemClick(name, id)
                }
            }
        }

        fun onBind(item: LocationsUI) = with(binding) {
            name.text = item.name
            type.text = item.type
            dimension.text = item.dimension
        }
    }
}
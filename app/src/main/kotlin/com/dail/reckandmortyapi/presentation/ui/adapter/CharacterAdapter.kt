package com.dail.reckandmortyapi.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.dail.reckandmortyapi.presentation.base.BaseComparator
import com.dail.reckandmortyapi.presentation.model.character.CharactersUI
import com.example.rikandmortyapi.databinding.CharactersItemBinding

class CharacterAdapter(
    val onItemClick: (name: String, id: Int) -> Unit,
    val fetchFirstSeenIn: (position: Int, episodeUrl: String) -> Unit,
    val onLongClick: (image: String) -> Unit
) : ListAdapter<CharactersUI, CharacterAdapter.ViewHolder>(BaseComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            CharactersItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position).let { it?.let { it1 -> holder.onBind(it1) } }
    }

    fun setFirstSeenIn(position: Int, firstSeenIn: String) {
        getItem(position)?.firstSeenIn = firstSeenIn
        notifyItemChanged(position, "payloadTitle")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            for (payload in payloads) {
                if (payload == "payloadTitle") {
                    holder.setupFirstSeenIn(
                        getItem(position)?.firstSeenIn.toString(),
                        getItem(position)?.episode?.first().toString()
                    )
                }
            }
        }
    }

    inner class ViewHolder(private val binding: CharactersItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                getItem(absoluteAdapterPosition)?.apply {
                    onItemClick(name, id)
                }
            }

            itemView.setOnLongClickListener {
                getItem(absoluteAdapterPosition)?.apply {
                    onLongClick(image)
                }
                false
            }

            binding.location.setOnClickListener {
                getItem(absoluteAdapterPosition)
            }

            binding.location2.setOnClickListener {
                getItem(absoluteAdapterPosition)
            }
        }

        fun onBind(item: CharactersUI): Unit = with(binding) {
            image.load(item.image)
            name.text = item.name
            when (item.status) {
                "Alive" -> statusColor.setImageResource(com.example.rikandmortyapi.R.drawable.status_alive)
                "Dead" -> statusColor.setImageResource(com.example.rikandmortyapi.R.drawable.status_dead)
                "unknown" -> statusColor.setImageResource(com.example.rikandmortyapi.R.drawable.status_unknown)
            }
            alive.text = item.status
            species.text = item.species
            with(location) {
                text = item.location.name
                isEnabled = item.location.url.isNotEmpty()
            }

            location.text = item.location.name
            location2.text = item.location.url
        }

        fun setupFirstSeenIn(firstSeenIn: String, episode: String) = with(binding) {
            if (firstSeenIn.isEmpty()) {
                fetchFirstSeenIn(absoluteAdapterPosition, episode)
            } else {
                location2.text = firstSeenIn
            }
        }

    }
}
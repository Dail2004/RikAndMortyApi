package com.dail.reckandmortyapi.presentation.base

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseViewHolder<out V : ViewBinding, in I : BaseDiffUtil>(
    val binding: V
) : RecyclerView.ViewHolder(binding.root) {
    abstract fun onBind(item: I)
}
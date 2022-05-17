package com.dail.reckandmortyapi.presentation.base.extension

import androidx.recyclerview.widget.RecyclerView
import com.dail.reckandmortyapi.presentation.base.BaseRequest

fun RecyclerView.scrollListenNextPage(viewModel: BaseRequest) {
    this.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                viewModel.page++
                viewModel.fetchCharacters(viewModel.page, "","")
            }
        }
    })
}
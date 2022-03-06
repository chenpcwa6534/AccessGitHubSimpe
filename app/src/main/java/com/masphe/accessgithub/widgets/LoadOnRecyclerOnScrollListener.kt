package com.masphe.accessgithub.widgets

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class LoadOnRecyclerOnScrollListener: RecyclerView.OnScrollListener() {

    private var isSlidingUpward = false

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        val manager = recyclerView.layoutManager as LinearLayoutManager

        if (newState == RecyclerView.SCROLL_STATE_IDLE){
            val lastItemPosition = manager.findLastCompletelyVisibleItemPosition()
            val itemCount = manager.itemCount

            if (lastItemPosition == (itemCount - 1) && this.isSlidingUpward){
                onLoad()
            }
        }
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        this.isSlidingUpward = dy > 0
    }


    abstract fun onLoad()
}
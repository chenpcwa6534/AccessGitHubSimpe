package com.masphe.accessgithub.ui.album.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.masphe.accessgithub.BR
import com.masphe.accessgithub.R
import com.masphe.accessgithub.dataCenter.api.response.Photo

class PhotoAdapter(private val photos: MutableList<Photo>, val listener: OnItemClickListener): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    interface OnItemClickListener{
        fun onClickPhoto(photoIndex: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ViewHolderStandard(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_photo, parent, false))

    override fun getItemCount(): Int = this.photos.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as ViewHolderStandard).binding
        binding.setVariable(BR.photo, this.photos[position])
        binding.root.setOnClickListener {
            this.listener.onClickPhoto(position)
        }
    }

    private class ViewHolderStandard(val binding: ViewDataBinding): RecyclerView.ViewHolder(binding.root)
}
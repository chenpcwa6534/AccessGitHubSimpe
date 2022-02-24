package com.masphe.accessgithub.ui.albumList.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.masphe.accessgithub.BR
import com.masphe.accessgithub.R
import com.masphe.accessgithub.dataCenter.api.response.Album

class AlbumAdapter(private val album: MutableList<Album>, val listener: OnItemClickListener): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    interface OnItemClickListener{
        fun onAlbum(album: Album)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ViewHolderStandard(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_album, parent, false))

    override fun getItemCount(): Int = this.album.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as ViewHolderStandard).binding
        binding.setVariable(BR.album, this.album[position])
        binding.root.setOnClickListener {
            this.listener.onAlbum(this.album[position])
        }
    }

    private class ViewHolderStandard(val binding: ViewDataBinding): RecyclerView.ViewHolder(binding.root)
}
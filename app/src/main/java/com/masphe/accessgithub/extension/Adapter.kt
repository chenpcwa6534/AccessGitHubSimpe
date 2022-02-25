package com.masphe.accessgithub.extension

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.masphe.accessgithub.dataCenter.api.response.Album
import com.masphe.accessgithub.dataCenter.api.response.Photo
import com.masphe.accessgithub.dataCenter.api.response.User
import com.masphe.accessgithub.extension.Adapter.setPhotos
import com.masphe.accessgithub.ui.album.view.AlbumAdapter
import com.masphe.accessgithub.ui.album.view.UserAdapter
import com.masphe.accessgithub.ui.gallery.view.GalleryAdapter

object Adapter{

    @JvmStatic
    @BindingAdapter("albums", "listener")
    fun RecyclerView.setAlbum(albums: MutableList<Album>?, listener: AlbumAdapter.OnItemClickListener){
        albums?.let {
            this.adapter = AlbumAdapter(albums, listener)
        }
    }

    @JvmStatic
    @BindingAdapter("users", "listener")
    fun RecyclerView.setUsers(users: MutableList<User>?, listener: UserAdapter.OnItemClickListener){
        users?.let {
            this.adapter = UserAdapter(users!!, listener)
        }
    }

    @JvmStatic
    @BindingAdapter("photos", "listener")
    fun RecyclerView.setPhotos(photos: MutableList<Photo>?, listener: GalleryAdapter.OnItemClickListener){
        photos?.let {
            this.adapter = GalleryAdapter(photos, listener)
        }
    }
}
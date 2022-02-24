package com.masphe.accessgithub.extension

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.masphe.accessgithub.dataCenter.api.response.Album
import com.masphe.accessgithub.dataCenter.api.response.Photo
import com.masphe.accessgithub.dataCenter.api.response.User
import com.masphe.accessgithub.ui.album.view.PhotoAdapter
import com.masphe.accessgithub.ui.albumList.view.AlbumAdapter
import com.masphe.accessgithub.ui.albumList.view.UserAdapter
import com.masphe.accessgithub.ui.users.model.Bean
import com.masphe.accessgithub.ui.users.view.UsersAdapter

object Adapter{
    @JvmStatic
    @BindingAdapter("data", "listener")
    fun RecyclerView.setUsers(data: Bean.Users?, listener: UsersAdapter.OnItemClickListener){
        if (data != null) this.adapter = UsersAdapter(data!!, listener)
    }

    @JvmStatic
    @BindingAdapter("isStaff")
    fun TextView.setStaff(isStaff: Boolean){
        if (isStaff) this.text = "Staff"
        else this.visibility = View.GONE
    }

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
    fun RecyclerView.setPhotos(photos: MutableList<Photo>?, listener: PhotoAdapter.OnItemClickListener){
        photos?.let {
            this.adapter = PhotoAdapter(photos, listener)
        }
    }
}
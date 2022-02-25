package com.masphe.accessgithub.ui.gallery.vm

import android.os.Bundle
import com.masphe.accessgithub.dataCenter.api.response.Album
import com.masphe.accessgithub.ui.Contract
import com.masphe.accessgithub.ui.base.BaseViewModel
import com.masphe.accessgithub.ui.gallery.view.GalleryAdapter

class ViewModel constructor(val uiState: UIState): BaseViewModel(), GalleryAdapter.OnItemClickListener{
    private val TAG = "Album vm"

    companion object{
        fun getInstance(uiState: UIState) = ViewModel(uiState)
    }

    init {
        this.uiState.onPhotoClickListener.value = this
    }

    fun getBundle(bundle: Bundle?){
        bundle?.let {
            val album = bundle.getParcelable<Album>(Contract.KeyName.Album)
            this.uiState.album.postValue(album)
        }
    }

    override fun onClickPhoto(photoIndex: Int) {
        this.uiState.onShowPicture.postValue(photoIndex)
    }
}
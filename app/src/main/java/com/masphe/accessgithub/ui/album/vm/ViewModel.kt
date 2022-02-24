package com.masphe.accessgithub.ui.album.vm

import android.os.Bundle
import com.masphe.accessgithub.dataCenter.api.response.Album
import com.masphe.accessgithub.ui.Contract
import com.masphe.accessgithub.ui.album.model.Model
import com.masphe.accessgithub.ui.album.view.PhotoAdapter
import com.masphe.accessgithub.ui.base.BaseViewModel

class ViewModel constructor(val uiState: UIState, private val model: Model): BaseViewModel(), PhotoAdapter.OnItemClickListener{
    private val TAG = "Album vm"

    companion object{
        fun getInstance(uiState: UIState, model: Model) = ViewModel(uiState, model)
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

    }
}
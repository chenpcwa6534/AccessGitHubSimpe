package com.masphe.accessgithub.ui.album.vm

import androidx.lifecycle.MutableLiveData
import com.masphe.accessgithub.dataCenter.api.response.Album
import com.masphe.accessgithub.ui.album.view.PhotoAdapter

class UIState {

    val album = MutableLiveData<Album>()
    val onPhotoClickListener = MutableLiveData<PhotoAdapter.OnItemClickListener>()
}
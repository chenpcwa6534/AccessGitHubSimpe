package com.masphe.accessgithub.ui.gallery.vm

import androidx.lifecycle.MutableLiveData
import com.masphe.accessgithub.dataCenter.api.response.Album
import com.masphe.accessgithub.ui.base.BaseUiState
import com.masphe.accessgithub.ui.gallery.view.GalleryAdapter

class UIState: BaseUiState() {

    val album = MutableLiveData<Album>()
    val onPhotoClickListener = MutableLiveData<GalleryAdapter.OnItemClickListener>()

    val onShowPicture = MutableLiveData<Int>()
}
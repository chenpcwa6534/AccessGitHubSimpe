package com.masphe.accessgithub.ui.albumList.vm

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.masphe.accessgithub.dataCenter.api.response.Album
import com.masphe.accessgithub.dataCenter.api.response.User
import com.masphe.accessgithub.ui.albumList.view.AlbumAdapter
import com.masphe.accessgithub.ui.albumList.view.UserAdapter
import com.masphe.accessgithub.ui.base.BaseUiState

class UIState : BaseUiState(){

    val data = MutableLiveData<String>()


    val users = MutableLiveData<MutableList<User>>()
    val onUserItemClick = MutableLiveData<UserAdapter.OnItemClickListener>()

    val albums = MutableLiveData<MutableList<Album>>()
    val OnAlbumItemClick = MutableLiveData<AlbumAdapter.OnItemClickListener>()

    val pageTrans = MutableLiveData<Bundle>()
}
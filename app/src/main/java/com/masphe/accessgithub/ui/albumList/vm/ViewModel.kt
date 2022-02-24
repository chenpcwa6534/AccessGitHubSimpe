package com.masphe.accessgithub.ui.albumList.vm

import android.os.Bundle
import android.util.Log
import com.masphe.accessgithub.dataCenter.api.response.Album
import com.masphe.accessgithub.dataCenter.api.response.User
import com.masphe.accessgithub.ui.Contract
import com.masphe.accessgithub.ui.albumList.model.Model
import com.masphe.accessgithub.ui.albumList.view.AlbumAdapter
import com.masphe.accessgithub.ui.albumList.view.UserAdapter
import com.masphe.accessgithub.ui.base.BaseViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

class ViewModel constructor(val uiState: UIState, private val model: Model) : BaseViewModel(), UserAdapter.OnItemClickListener, AlbumAdapter.OnItemClickListener {
    private val TAG = "Album vm"

    companion object{
        fun getInstance(uiState: UIState, model: Model) = ViewModel(uiState, model)
    }

    init {
        this.uiState.onUserItemClick.value = this
        this.uiState.OnAlbumItemClick.value = this
        getUsers()
    }

    private fun getUsers(){
        CoroutineScope(Dispatchers.IO).launch {
            this@ViewModel.model.getUsers().collect {
                withContext(Dispatchers.Main){
                    this@ViewModel.uiState.users.postValue(it)
                    this@ViewModel.uiState.albums.postValue(it[0].albums)
                }
            }
        }
    }

    private fun getAlbum(user: User){
        CoroutineScope(Dispatchers.IO).launch {
            this@ViewModel.model.getAlbums(user).collect {
                Log.i(TAG, "api is call ${user.id}")
                this@ViewModel.uiState.albums.postValue(it)
            }
        }
    }

    override fun onClickUser(user: User) {
        getAlbum(user)
    }

    override fun onAlbum(album: Album) {
        val bundle = Bundle()
        bundle.putParcelable(Contract.KeyName.Album, album)
        this.uiState.pageTrans.postValue(bundle)
    }
}
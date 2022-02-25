package com.masphe.accessgithub.ui.album.vm

import android.os.Bundle
import com.masphe.accessgithub.dataCenter.api.HttpResult
import com.masphe.accessgithub.dataCenter.api.response.Album
import com.masphe.accessgithub.dataCenter.api.response.User
import com.masphe.accessgithub.ui.Contract
import com.masphe.accessgithub.ui.album.model.Model
import com.masphe.accessgithub.ui.album.view.AlbumAdapter
import com.masphe.accessgithub.ui.album.view.UserAdapter
import com.masphe.accessgithub.ui.base.BaseViewModel
import kotlinx.coroutines.*

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
        this@ViewModel.uiState.isShowProgress.value = true
        CoroutineScope(Dispatchers.IO).launch {
            val response = this@ViewModel.model.getUsers()
            withContext(Dispatchers.Main){
                when(response){
                    is HttpResult.onSuccess -> {
                        response.data?.let {
                            this@ViewModel.uiState.users.postValue(response.data)
                            this@ViewModel.uiState.albums.postValue(response.data[0].albums)
                        }
                    }
                    is HttpResult.onError -> {
                        this@ViewModel.uiState.errorDialogMsg.postValue(response.message)
                        this@ViewModel.uiState.isShowErrorDialog.postValue(true)
                    }
                }
                this@ViewModel.uiState.isShowProgress.value = false
            }
        }
    }

    private fun getAlbum(user: User){
        this@ViewModel.uiState.isShowProgress.value = true
        CoroutineScope(Dispatchers.IO).launch {
            val response = this@ViewModel.model.getAlbums(user)
            withContext(Dispatchers.Main){
                when(response){
                    is HttpResult.onSuccess -> {
                        response.data?.let {
                            this@ViewModel.uiState.albums.postValue(it)
                        }
                    }
                    is HttpResult.onError ->{
                        this@ViewModel.uiState.errorDialogMsg.postValue(response.message)
                        this@ViewModel.uiState.isShowErrorDialog.postValue(true)
                    }
                }
                this@ViewModel.uiState.isShowProgress.value = false
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
package com.masphe.accessgithub.ui.user.vm

import android.os.Bundle
import com.masphe.accessgithub.dataCenter.api.HttpResult
import com.masphe.accessgithub.ui.Contract
import com.masphe.accessgithub.ui.base.BaseViewModel
import com.masphe.accessgithub.ui.user.model.Model
import kotlinx.coroutines.*

class ViewModel constructor(private val model: Model) : BaseViewModel(){
    private val TAG = "User VM"

    private val uiState = UIState()

    companion object{
        fun getInstance(model: Model) = ViewModel(model)
    }

    fun getUIState() = this.uiState

    fun getBundle(bundle: Bundle?){
        if (bundle != null){
            getUserInfo(bundle!!.getString(Contract.KEY_USER_NAME)!!)
        }
    }

    private fun getUserInfo(name: String){
        this.uiState.isShowProgress.postValue(true)
        CoroutineScope(Dispatchers.IO).launch {
            val response = this@ViewModel.model.getUser(name)
            withContext(Dispatchers.Default){
                this@ViewModel.uiState.isShowProgress.postValue(false)
                when(response){
                    is HttpResult.onSuccess -> this@ViewModel.uiState.user.postValue(response.data)
                    is HttpResult.onError -> this@ViewModel.uiState.isShowErrorDialog.postValue(response.message)
                }
            }
        }
    }
}
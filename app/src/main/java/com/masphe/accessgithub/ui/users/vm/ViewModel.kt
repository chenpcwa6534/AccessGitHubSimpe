package com.masphe.accessgithub.ui.users.vm

import android.os.Bundle
import com.masphe.accessgithub.dataCenter.api.HttpResult
import com.masphe.accessgithub.ui.Contract
import com.masphe.accessgithub.ui.base.BaseViewModel
import com.masphe.accessgithub.ui.users.model.Model
import com.masphe.accessgithub.ui.users.view.UsersAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ViewModel constructor(private val model: Model): BaseViewModel(), UsersAdapter.OnItemClickListener{
    private val TAG = "Users VM"


    private val uiState = UIState()

    companion object{
        fun getInstance(model: Model) =
            ViewModel(model)
    }

    fun getUIState() = this.uiState

    init {
        this.uiState.onItemListener.value = this
        getUserList()
    }

    private fun getUserList(){
        this.uiState.isShowProgress.postValue(true)
        CoroutineScope(Dispatchers.IO).launch {
            val response = this@ViewModel.model.getUsers()
            withContext(Dispatchers.Default){
                this@ViewModel.uiState.isShowProgress.postValue(false)
                when(response){
                    is HttpResult.onSuccess -> this@ViewModel.uiState.users.postValue(response.data)
                    is HttpResult.onError -> this@ViewModel.uiState.isShowErrorDialog.postValue(response.message)
                }
            }
        }
    }

    override fun onItemClick(userName: String) {
        val bundle = Bundle()
        bundle.putString(Contract.KEY_USER_NAME, userName)
        this.uiState.pageTrans.postValue(bundle)
    }
}
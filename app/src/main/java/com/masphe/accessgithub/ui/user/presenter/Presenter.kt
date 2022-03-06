package com.masphe.accessgithub.ui.user.presenter

import com.masphe.accessgithub.dataCenter.api.HttpResult
import com.masphe.accessgithub.ui.base.BaseViewModel
import com.masphe.accessgithub.ui.user.model.IModel
import com.masphe.accessgithub.ui.user.view.IView
import kotlinx.coroutines.*

class Presenter constructor(private val view: IView, private val model: IModel) : BaseViewModel(), IPresenter{
    private val TAG = "User VM"

    companion object{
        fun getInstance(view: IView, model: IModel) = Presenter(view, model)
    }

    override fun getUserInfo(userName: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = this@Presenter.model.getUser(userName)
            withContext(Dispatchers.Main){
                when(response){
                    is HttpResult.onSuccess -> {
                        response.data?.let {
                            this@Presenter.view.updateUserInfo(response.data)
                        }?: this@Presenter.view.showUserNameNullError()
                    }
                    is HttpResult.onError -> this@Presenter.view.onShowErrorDialog(response.message)
                }
            }
        }
    }

//    private fun getUserInfo(name: String){
//        this.uiState.isShowProgress.postValue(true)
//        CoroutineScope(Dispatchers.IO).launch {
//            val response = this@Presenter.model.getUser(name)
//            withContext(Dispatchers.Default){
//                this@Presenter.uiState.isShowProgress.postValue(false)
//                when(response){
//                    is HttpResult.onSuccess -> this@Presenter.uiState.user.postValue(response.data)
//                    is HttpResult.onError -> this@Presenter.uiState.isShowErrorDialog.postValue(response.message)
//                }
//            }
//        }
//    }
}
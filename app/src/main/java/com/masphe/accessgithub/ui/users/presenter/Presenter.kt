package com.masphe.accessgithub.ui.users.presenter

import com.masphe.accessgithub.dataCenter.api.HttpResult
import com.masphe.accessgithub.ui.base.BaseViewModel
import com.masphe.accessgithub.ui.users.model.IModel
import com.masphe.accessgithub.ui.users.view.IView
import kotlinx.coroutines.*

class Presenter constructor(private val view: IView, private val model: IModel): BaseViewModel(), IPresenter{
    private val TAG = "Users VM"

    companion object{
        fun getInstance(view: IView, model: IModel) =
            Presenter(view, model)
    }

    override fun onLoadUser() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = this@Presenter.model.getUsers()
            withContext(Dispatchers.Main){
                when(response){
                    is HttpResult.onSuccess -> {
                        response?.let {
                            this@Presenter.view.updateUsers(response.data!!)
                        }
                    }
                    is HttpResult.onError -> this@Presenter.view.onShowErrorDialog(response.message)
                }
            }
        }
    }

    override fun onLoadOnNextPage() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = this@Presenter.model.getUsersNext()
            withContext(Dispatchers.Main){
                when(response){
                    is HttpResult.onSuccess -> this@Presenter.view.addUsers(users = response.data)
                    is HttpResult.onError -> this@Presenter.view.addUsers(isEnd = true)
                }
            }
        }
    }
}
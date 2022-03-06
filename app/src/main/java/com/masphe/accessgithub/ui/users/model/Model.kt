package com.masphe.accessgithub.ui.users.model

import android.content.Context
import com.masphe.accessgithub.dataCenter.Repository
import com.masphe.accessgithub.dataCenter.api.Contract
import com.masphe.accessgithub.dataCenter.api.HttpResult
import com.masphe.accessgithub.dataCenter.api.StatusCode
import com.masphe.accessgithub.dataCenter.api.response.User
import com.masphe.accessgithub.ui.base.BaseModel

class Model constructor(private val context: Context, private val repository: Repository): BaseModel(), IModel{

    private val cacheUsers = mutableListOf<User>()

    companion object{
        fun getInstance(context: Context, repository: Repository) = Model(context, repository)
    }

    override suspend fun getUsers(): HttpResult<MutableList<User>> {
        val response = this.repository.getUsers()
        this.cacheUsers.clear()
        addCache(response)
        return response
    }

    override suspend fun getUsersNext(): HttpResult<MutableList<User>> {
        if (this.cacheUsers.size >= Contract.usersQtyLimit){
            return HttpResult.onError(StatusCode.NOT_DATA.errorCode, this.context.getString(StatusCode.NOT_DATA.errorMsgForUser))
        }else{
            val response = this.repository.getUsers(this.cacheUsers[this.cacheUsers.lastIndex].id)
            addCache(response)
            return response
        }
    }

    private fun addCache(response: HttpResult<MutableList<User>>){
        when(response){
            is HttpResult.onSuccess -> {
                response.data?.let {
                    this.cacheUsers.addAll(response.data)
                }
            }
        }
    }
}
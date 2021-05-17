package com.masphe.accessgithub.ui.users.model

import com.masphe.accessgithub.dataCenter.Repository
import com.masphe.accessgithub.dataCenter.api.HttpResult
import com.masphe.accessgithub.dataCenter.api.StatusCode
import com.masphe.accessgithub.dataCenter.api.response.User
import com.masphe.accessgithub.ui.base.BaseModel

class Model constructor(private val repository: Repository): BaseModel(), IModel{

    private val userList = Bean.Users(mutableListOf())

    companion object{
        fun getInstance(repository: Repository) = Model(repository)
    }

    override suspend fun getUsers(): HttpResult<Bean.Users> {
        val response = this.repository.getUsers()
        return when (response) {
            is HttpResult.onSuccess -> if (response.data.isNullOrEmpty()){
                HttpResult.onError(StatusCode.NoData.errorCode, StatusCode.NoData.errorMsg)
            }else {
                HttpResult.onSuccess(getUser(response.data))
            }
            is HttpResult.onError -> HttpResult.onError(response.code, response.message)
        }
    }

    private fun getUser(response: MutableList<User>): Bean.Users{
        response.forEach {
            val user = Bean.User(it.avatar_url, it.login, it.site_admin)
            this.userList.users.add(user)
        }
        return this.userList
    }
}
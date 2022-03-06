package com.masphe.accessgithub.ui.users.model

import com.masphe.accessgithub.dataCenter.api.HttpResult
import com.masphe.accessgithub.dataCenter.api.response.User

interface IModel{
    suspend fun getUsers(): HttpResult<MutableList<User>>
    suspend fun getUsersNext() : HttpResult<MutableList<User>>
}
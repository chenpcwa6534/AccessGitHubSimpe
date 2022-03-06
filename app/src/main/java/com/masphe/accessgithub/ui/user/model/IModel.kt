package com.masphe.accessgithub.ui.user.model

import com.masphe.accessgithub.dataCenter.api.HttpResult
import com.masphe.accessgithub.dataCenter.api.response.User

interface IModel{
    suspend fun getUser(name: String): HttpResult<User>
}
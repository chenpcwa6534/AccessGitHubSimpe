package com.masphe.accessgithub.ui.user.model

import com.masphe.accessgithub.dataCenter.api.HttpResult

interface IModel{
    suspend fun getUser(name: String): HttpResult<Bean.User>
}
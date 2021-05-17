package com.masphe.accessgithub.ui.users.model

import com.masphe.accessgithub.dataCenter.api.HttpResult

interface IModel{
    suspend fun getUsers(): HttpResult<Bean.Users>
}
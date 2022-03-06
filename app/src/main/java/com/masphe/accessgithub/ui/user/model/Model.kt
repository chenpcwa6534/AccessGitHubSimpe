package com.masphe.accessgithub.ui.user.model

import android.content.Context
import com.masphe.accessgithub.dataCenter.Repository
import com.masphe.accessgithub.dataCenter.api.HttpResult
import com.masphe.accessgithub.dataCenter.api.response.User
import com.masphe.accessgithub.ui.base.BaseModel


class Model constructor(private val context: Context, private val repository: Repository): BaseModel(), IModel{

    companion object{
        fun getInstance(context: Context, repository: Repository) = Model(context, repository)
    }

    override suspend fun getUser(name: String): HttpResult<User> {
        val response = this.repository.getUser(name)
        return response
    }
}
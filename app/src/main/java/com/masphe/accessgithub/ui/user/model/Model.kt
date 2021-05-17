package com.masphe.accessgithub.ui.user.model

import com.masphe.accessgithub.dataCenter.Repository
import com.masphe.accessgithub.dataCenter.api.HttpResult
import com.masphe.accessgithub.dataCenter.api.StatusCode
import com.masphe.accessgithub.dataCenter.api.response.User
import com.masphe.accessgithub.ui.base.BaseModel


class Model constructor(private val repository: Repository): BaseModel(), IModel{

    companion object{
        fun getInstance(repository: Repository) = Model(repository)
    }

    override suspend fun getUser(name: String): HttpResult<Bean.User> {
        val response = this.repository.getUser(name)
        return when(response){
            is HttpResult.onSuccess -> if (response.data != null){
                HttpResult.onSuccess(getUserInfo(response.data))
            }else{
                HttpResult.onError(StatusCode.NoData.errorCode, StatusCode.NoData.errorMsg)
            }
            is HttpResult.onError -> HttpResult.onError(response.code, response.message)
        }
    }

    private fun getUserInfo(response: User): Bean.User =
        Bean.User(
            response.avatar_url,
            response.bio,
            response.login,
            response.name,
            response.site_admin,
            response.location,
            response.blog
        )
}
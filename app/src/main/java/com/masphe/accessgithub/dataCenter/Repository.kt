package com.masphe.accessgithub.dataCenter

import com.masphe.accessgithub.dataCenter.api.HttpResult
import com.masphe.accessgithub.dataCenter.api.StatusCode
import com.masphe.accessgithub.dataCenter.api.response.User
import com.masphe.accessgithub.dataCenter.api.service.UserService
import retrofit2.Retrofit

class Repository constructor(private val client: Retrofit){

    private fun getUserService(): UserService = this.client.create(UserService::class.java)

    suspend fun getUsers(): HttpResult<MutableList<User>> =
        try {
            val response = getUserService().getUsers().await()
            if (response.isNullOrEmpty().not()){
                HttpResult.onSuccess(response)
            }else{
                HttpResult.onError(StatusCode.NotFound.errorCode, StatusCode.NotFound.errorMsg)
            }
        }catch (e: Throwable){
            HttpResult.onError(StatusCode.SystemParseError.errorCode, StatusCode.SystemParseError.errorMsg)
        }

    suspend fun getUser(name: String): HttpResult<User> =
        try {
            val response = getUserService().getUser(name).await()
            if (response != null){
                HttpResult.onSuccess(response)
            }else{
                HttpResult.onError(StatusCode.NotFound.errorCode, StatusCode.NotFound.errorMsg)
            }
        }catch (e: Throwable){
            HttpResult.onError(StatusCode.SystemParseError.errorCode, StatusCode.SystemParseError.errorMsg)
        }
}
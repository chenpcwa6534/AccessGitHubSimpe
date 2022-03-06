package com.masphe.accessgithub.dataCenter

import android.content.Context
import android.util.Log
import com.masphe.accessgithub.dataCenter.api.Contract
import com.masphe.accessgithub.dataCenter.api.HttpResult
import com.masphe.accessgithub.dataCenter.api.StatusCode
import com.masphe.accessgithub.dataCenter.api.response.User
import com.masphe.accessgithub.dataCenter.api.service.UserService
import retrofit2.Retrofit

class Repository constructor(private val context: Context, private val client: Retrofit){
    private val TAG = "Repository"

    private fun getUserService(): UserService = this.client.create(UserService::class.java)

    suspend fun getUsers(since: Int = Contract.defaultSince): HttpResult<MutableList<User>> {
        val response = getUserService().getUsers(since).await()
        return if (response.isSuccessful){
            HttpResult.onSuccess(response.body())
        }else {
            getErrorResult(response.code())
        }
    }

    suspend fun getUser(name: String): HttpResult<User> {
        val response = getUserService().getUser(name).await()
        return if (response.isSuccessful){
            HttpResult.onSuccess(response.body())
        }else {
            getErrorResult(response.code())
        }
    }

    private fun getErrorResult(errorCode: Int): HttpResult.onError {
        val result = when(errorCode){
            StatusCode.FORIBIDDEN.errorCode -> StatusCode.FORIBIDDEN
            StatusCode.NOT_MODIFIE.errorCode -> StatusCode.NOT_MODIFIE
            StatusCode.UNAUTHORIZED.errorCode -> StatusCode.UNAUTHORIZED
            StatusCode.UNSUPPORTED.errorCode -> StatusCode.UNSUPPORTED
            StatusCode.UNPROCESSABLE.errorCode -> StatusCode.UNPROCESSABLE
            else -> StatusCode.NOT_FOUND
        }
        Log.e(TAG, "ErrorCode: ${result.errorCode}\nMessage: ${result.errorMsg}")

        return HttpResult.onError(result.errorCode, this.context.getString(result.errorMsgForUser))
    }
}
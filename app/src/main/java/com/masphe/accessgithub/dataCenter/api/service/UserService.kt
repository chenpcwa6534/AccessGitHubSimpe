package com.masphe.accessgithub.dataCenter.api.service


import com.masphe.accessgithub.dataCenter.api.Contract
import com.masphe.accessgithub.dataCenter.api.response.User
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService{
    @Headers(
        Contract.HEADER_ACCEPT
    )
    @GET("users")
    fun getUsers(@Query("since") since: Int, @Query("per_page") perPage: Int = Contract.usersQtyOfPage): Deferred<Response<MutableList<User>>>

    @Headers(
        Contract.HEADER_ACCEPT
    )
    @GET("users/{username}")
    fun getUser(@Path("username") name: String): Deferred<Response<User>>
}
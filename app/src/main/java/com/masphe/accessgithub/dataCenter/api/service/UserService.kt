package com.masphe.accessgithub.dataCenter.api.service


import com.masphe.accessgithub.dataCenter.api.response.User
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface UserService{
    @Headers(
        "Accept:application/vnd.github.v3+json",
        "per_page:100"
    )
    @GET("users")
    fun getUsers(): Deferred<MutableList<User>>

    @Headers(
        "Accept:application/vnd.github.v3+json",
        "per_page:100"
    )
    @GET("users/{username}")
    fun getUser(@Path("username") name: String): Deferred<User>
}
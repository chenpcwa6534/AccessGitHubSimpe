package com.masphe.accessgithub.dataCenter.api

class Contract{
    val connectTimeout: Long = 30

    private val host_release = "https://jsonplaceholder.typicode.com/"
    fun getHostUrl(): String = host_release

}
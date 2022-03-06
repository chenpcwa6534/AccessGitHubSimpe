package com.masphe.accessgithub.dataCenter.api

class Contract{


    companion object{
        const val connectTimeout: Long = 30

        const val usersQtyLimit = 100
        const val usersQtyOfPage = 20
        const val defaultSince = 0

        private const val host_release = "https://api.github.com"
        fun getHostUrl(): String = host_release

        const val HEADER_ACCEPT = "Accept:application/vnd.github.v3+json"
    }
}
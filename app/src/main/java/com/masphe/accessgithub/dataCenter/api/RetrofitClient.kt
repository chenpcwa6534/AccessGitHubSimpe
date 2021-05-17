package com.masphe.accessgithub.dataCenter.api

import android.content.Context
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class RetrofitClient constructor(private val context: Context){
    private val TAG = "Retrofit Client"

    private val url = "https://api.github.com"

    companion object{
        fun getInstance(context: Context) = RetrofitClient(context)
    }

    fun build(): Retrofit =
        Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(getOkHttpClient())
            .build()

    private fun getOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()
}
package com.masphe.accessgithub.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.masphe.accessgithub.dataCenter.Repository
import com.masphe.accessgithub.dataCenter.api.Contract
import com.masphe.accessgithub.dataCenter.api.RetrofitClient
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val retrofitClient = module {
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(Contract().connectTimeout, TimeUnit.SECONDS)
            .build()
    }

    fun provideConverter(): Gson =
        GsonBuilder()
            .setLenient()
            .create()

    fun provideRetrofit(client: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Contract().getHostUrl())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(client)
            .build()
    }

    fun provideRepository(context: Context, client: Retrofit) =
        Repository(context, client)

    single { provideOkHttpClient() }
    single { provideConverter() }
    single { provideRetrofit(get(), get()) }
    single { provideRepository(get(), get()) }
}
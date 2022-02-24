package com.masphe.accessgithub.dataCenter.api.service

import com.masphe.accessgithub.dataCenter.api.response.Album
import com.masphe.accessgithub.dataCenter.api.response.Photo
import com.masphe.accessgithub.dataCenter.api.response.User
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TypicodeService {

    @GET("users")
    fun getUsers(): Deferred<Response<MutableList<User>>>

    @GET("albums")
    fun getAllAlbums(): Deferred<Response<MutableList<Album>>>

    @GET("albums")
    fun getAlbum(@Query("userId") userId: Int): Deferred<Response<MutableList<Album>>>

    @GET("photos")
    fun getAllPhotos(): Deferred<Response<MutableList<Photo>>>

    @GET("photos")
    fun getPhotosOfAlbum(@Query("albumId") albumId: Int): Deferred<Response<MutableList<Photo>>>

}
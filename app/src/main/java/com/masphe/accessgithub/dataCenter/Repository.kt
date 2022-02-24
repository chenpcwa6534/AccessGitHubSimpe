package com.masphe.accessgithub.dataCenter

import android.content.Context
import com.masphe.accessgithub.dataCenter.api.HttpResult
import com.masphe.accessgithub.dataCenter.api.StatusCode
import com.masphe.accessgithub.dataCenter.api.response.*
import com.masphe.accessgithub.dataCenter.api.service.TypicodeService
import com.masphe.accessgithub.dataCenter.api.service.UserService
import com.masphe.accessgithub.widgets.LoadingDialog
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import retrofit2.Retrofit

class Repository constructor(private val context: Context, private val client: Retrofit){

    private var loadingDialog: LoadingDialog
    private fun getTypicodeService(): TypicodeService = this.client.create(TypicodeService::class.java)

    init {
        this.loadingDialog = LoadingDialog(this.context)
    }

    suspend fun getUsers(): Flow<MutableList<User>> =
        flow {
            val responseOfUsers = this@Repository.getTypicodeService().getUsers().await()
            if (responseOfUsers.isSuccessful){
                responseOfUsers.body()?.let {
                    this@Repository.getAlbums(responseOfUsers.body()!![0].id).collect { responseOfAlbums ->
                        if (it.isNullOrEmpty().not()){
                            responseOfUsers.body()!![0].albums = responseOfAlbums
                            emit(responseOfUsers.body()!!)
                        }
                    }
                }
            }
        }

    suspend fun getAlbums(userId: Int): Flow<MutableList<Album>> =
        flow {
            val responseOfAlbums = this@Repository.getTypicodeService().getAlbum(userId).await()
            if (responseOfAlbums.isSuccessful){
                responseOfAlbums.body()?.forEach {
                    this@Repository.getPhoto(it.id).collect { responsePhotos ->
                        it.photo = responsePhotos
                    }
                }

                if (responseOfAlbums.body().isNullOrEmpty()) emit(mutableListOf())
                else emit(responseOfAlbums.body()!!)
            }
        }

    suspend fun getPhoto(albumId: Int): Flow<MutableList<Photo>> =
        flow {
            val response = this@Repository.getTypicodeService().getPhotosOfAlbum(albumId).await()
            if (response.isSuccessful){
                if (response.body().isNullOrEmpty()) emit(mutableListOf())
                else emit(response.body()!!)
            }
        }

    private fun getUserService(): UserService = this.client.create(UserService::class.java)

    suspend fun getOldUsers(): HttpResult<MutableList<UserForOld>> =
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

    suspend fun getUser(name: String): HttpResult<UserForOld> =
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
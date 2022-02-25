package com.masphe.accessgithub.dataCenter

import android.content.Context
import android.util.Log
import com.masphe.accessgithub.dataCenter.api.HttpResult
import com.masphe.accessgithub.dataCenter.api.StatusCode
import com.masphe.accessgithub.dataCenter.api.response.*
import com.masphe.accessgithub.dataCenter.api.service.TypicodeService
import retrofit2.Retrofit

class Repository constructor(private val context: Context, private val client: Retrofit){
    private val TAG_NETWORK = "Network"

    private fun getTypicodeService(): TypicodeService = this.client.create(TypicodeService::class.java)

    suspend fun getUsers(): HttpResult<MutableList<User>> {
        val responseOfUsers = this@Repository.getTypicodeService().getUsers().await()
        return if (responseOfUsers.isSuccessful){
            responseOfUsers.body()?.let { users ->
                val responseOfAlbum = this@Repository.getAlbums(users[0].id)
                when(responseOfAlbum){
                    is HttpResult.onSuccess -> {
                        responseOfAlbum.data?.let {
                            users[0].albums = responseOfAlbum.data
                        }
                    }
                    else -> {}
                }
            }
            HttpResult.onSuccess(responseOfUsers.body())
        }else{
            val status = getErrorStatus(responseOfUsers.code())
            showLog(status)
            HttpResult.onError(responseOfUsers.code(), this.context.getString(status.userErrorMsg))
        }
    }

    suspend fun getAlbums(userId: Int): HttpResult<MutableList<Album>> {
        val responseOfAlbums = this@Repository.getTypicodeService().getAlbum(userId).await()
        return if (responseOfAlbums.isSuccessful){
            responseOfAlbums.body()?.forEach { album ->
                val responseOfPhoto = this@Repository.getPhoto(album.id)
                when(responseOfPhoto){
                    is HttpResult.onSuccess -> {
                        responseOfPhoto.data?.let {
                            album.photo = responseOfPhoto.data
                        }
                    }
                }
            }
            HttpResult.onSuccess(responseOfAlbums.body())
        }else{
            val status = getErrorStatus(responseOfAlbums.code())
            showLog(status)
            HttpResult.onError(responseOfAlbums.code(), this.context.getString(status.userErrorMsg))
        }
    }

    suspend fun getPhoto(albumId: Int): HttpResult<MutableList<Photo>> {
        val response = this@Repository.getTypicodeService().getPhotosOfAlbum(albumId).await()
        return if (response.isSuccessful){
            HttpResult.onSuccess(response.body())
        }else{
            val status = getErrorStatus(response.code())
            showLog(status)
            HttpResult.onError(response.code(), this.context.getString(status.userErrorMsg))
        }
    }

    private fun showLog(status: StatusCode){
        Log.e(TAG_NETWORK, "ErrorCode: ${status.errorCode}\nMessage: ${status.errorMsg}")
    }

    private fun getErrorStatus(code: Int): StatusCode =
        when(code){
            StatusCode.NoData.errorCode -> StatusCode.NoData
            else -> StatusCode.NotFound
        }
}
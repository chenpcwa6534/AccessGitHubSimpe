package com.masphe.accessgithub.ui.album.model

import com.masphe.accessgithub.dataCenter.Repository
import com.masphe.accessgithub.dataCenter.api.HttpResult
import com.masphe.accessgithub.dataCenter.api.response.Album
import com.masphe.accessgithub.dataCenter.api.response.User
import com.masphe.accessgithub.ui.base.BaseModel

class Model constructor(private val repository: Repository): BaseModel() {

    private lateinit var cacheForUser: MutableList<User>

    companion object{
        fun getInstance(repository: Repository) = Model(repository)
    }

    suspend fun getUsers(): HttpResult<MutableList<User>> {
        val response = this@Model.repository.getUsers()
        when(response){
            is HttpResult.onSuccess -> {
                response.data?.let {
                    it[0].isSelected = true
                    this.cacheForUser = it
                }
            }
        }
        return response
    }

    suspend fun getAlbums(user: User): HttpResult<MutableList<Album>> {
        val cacheOfUser = this@Model.cacheForUser.get(this@Model.cacheForUser.indexOf(user))
        return if (cacheOfUser.albums.isNullOrEmpty()) {
            val response = this@Model.repository.getAlbums(user.id)
            when(response){
                is HttpResult.onSuccess -> {
                    response.data?.let {
                        cacheOfUser.albums = it
                    }
                    HttpResult.onSuccess(response.data)
                }
                else -> response
            }
        }else{
            HttpResult.onSuccess(cacheOfUser.albums)
        }
    }
}
package com.masphe.accessgithub.ui.albumList.model

import com.masphe.accessgithub.dataCenter.Repository
import com.masphe.accessgithub.dataCenter.api.response.Album
import com.masphe.accessgithub.dataCenter.api.response.User
import com.masphe.accessgithub.ui.base.BaseModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class Model constructor(private val repository: Repository): BaseModel() {

    private lateinit var cacheForUser: MutableList<User>

    companion object{
        fun getInstance(repository: Repository) = Model(repository)
    }

    suspend fun getUsers(): Flow<MutableList<User>> =
        flow {
            this@Model.repository.getUsers().collect {
                it?.let {
                    it[0].isSelected = true
                }
                this@Model.cacheForUser = it
                emit(it)
            }
        }

    suspend fun getAlbums(user: User): Flow<MutableList<Album>> =
        flow {
            val cacheOfUser = this@Model.cacheForUser.get(this@Model.cacheForUser.indexOf(user))
            if (cacheOfUser.albums.isNullOrEmpty()) {
                this@Model.repository.getAlbums(user.id).collect {
                    cacheOfUser.albums = it
                    emit(it)
                }
            }else{
                emit(cacheOfUser.albums)
            }
        }
}
package com.masphe.accessgithub.ui.album.model

import com.masphe.accessgithub.dataCenter.Repository
import com.masphe.accessgithub.ui.base.BaseModel

class Model constructor(private val repository: Repository): BaseModel() {

    companion object{
        fun getInstance(repository: Repository) = Model(repository)
    }
}
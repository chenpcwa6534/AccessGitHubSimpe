package com.masphe.accessgithub.ui.users.view

import com.masphe.accessgithub.dataCenter.api.response.User
import com.masphe.accessgithub.ui.base.IBaseView

interface IView: IBaseView {
    fun updateUsers(users: MutableList<User>)
    fun addUsers(isEnd: Boolean = false, users: MutableList<User>? = null)
}
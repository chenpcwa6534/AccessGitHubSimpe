package com.masphe.accessgithub.ui.user.view

import com.masphe.accessgithub.dataCenter.api.response.User
import com.masphe.accessgithub.ui.base.IBaseView

interface IView: IBaseView {
    fun updateUserInfo(user: User)
    fun showUserNameNullError()
}
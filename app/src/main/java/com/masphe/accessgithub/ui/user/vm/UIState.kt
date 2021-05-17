package com.masphe.accessgithub.ui.user.vm

import androidx.lifecycle.MutableLiveData
import com.masphe.accessgithub.ui.base.BaseUiState
import com.masphe.accessgithub.ui.user.model.Bean.User

class UIState : BaseUiState(){
    /** UI Binding **/
    val user = MutableLiveData<User>()
}
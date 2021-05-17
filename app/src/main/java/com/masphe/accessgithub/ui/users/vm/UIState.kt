package com.masphe.accessgithub.ui.users.vm

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.masphe.accessgithub.ui.base.BaseUiState
import com.masphe.accessgithub.ui.users.model.Bean
import com.masphe.accessgithub.ui.users.view.UsersAdapter

class UIState: BaseUiState(){
    /** UI Binding **/
    val users = MutableLiveData<Bean.Users>()


    /** Event **/
    val onItemListener = MutableLiveData<UsersAdapter.OnItemClickListener>()

    /** Page Transform */
    val pageTrans = MutableLiveData<Bundle>()
}
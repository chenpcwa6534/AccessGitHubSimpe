package com.masphe.accessgithub.ui.base

import androidx.lifecycle.MutableLiveData
import com.masphe.accessgithub.extension.default

open class BaseUiState{
    val isShowProgress = MutableLiveData<Boolean>().default(false)
    val errorDialogMsg = MutableLiveData<String>()
    val isShowErrorDialog = MutableLiveData<Boolean>()
}
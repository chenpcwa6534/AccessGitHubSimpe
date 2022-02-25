package com.masphe.accessgithub.dataCenter.api

import com.masphe.accessgithub.R

enum class StatusCode(val errorCode: Int, val errorMsg: Int, val userErrorMsg: Int){
    NotFound(404, R.string.error_404, R.string.error_user_404),
    NoData(902, R.string.error_902, R.string.error_user_902),
    THROWABLE(999, R.string.error_throwable, R.string.error_user_throwable)
}
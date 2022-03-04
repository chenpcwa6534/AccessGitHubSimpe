package com.masphe.accessgithub.dataCenter.api

import com.masphe.accessgithub.R

enum class StatusCode(val errorCode: Int, val errorMsg: Int, val errorMsgForUser: Int){
    NOT_MODIFIE(304, R.string.error_message_304, R.string.error_client_error),
    UNAUTHORIZED(401, R.string.error_message_401, R.string.error_client_error),
    FORIBIDDEN(403, R.string.error_message_403, R.string.error_client_error),
    NOT_FOUND(404, R.string.error_message_404, R.string.error_client_try_again),
    UNSUPPORTED(415, R.string.error_message_415, R.string.error_client_error),
    UNPROCESSABLE(422, R.string.error_message_422, R.string.error_client_try_again),
    NOT_DATA(227, R.string.error_message_227, R.string.error_client_no_data)
}
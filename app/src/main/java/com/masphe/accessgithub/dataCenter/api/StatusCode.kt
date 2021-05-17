package com.masphe.accessgithub.dataCenter.api

enum class StatusCode(val errorCode: Int, val errorMsg: String){
    NotFound(404, "Data Not Found."),
    NoData(902, "This is no data."),
    SystemParseError(901, "Application an error occurred, please try again.")
}
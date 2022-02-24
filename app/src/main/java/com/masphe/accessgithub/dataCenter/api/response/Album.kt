package com.masphe.accessgithub.dataCenter.api.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Album(
    val id: Int,
    val title: String,
    val userId: Int,
    var photo: MutableList<Photo>
): Parcelable
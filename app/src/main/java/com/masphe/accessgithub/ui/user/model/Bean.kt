package com.masphe.accessgithub.ui.user.model

class Bean {
    data class User(
        val avatar_url: String?,
        val bio: String?,
        val login: String?,
        val name: String?,
        val site_admin: Boolean,
        val location: String?,
        val blog: String?
    )
}
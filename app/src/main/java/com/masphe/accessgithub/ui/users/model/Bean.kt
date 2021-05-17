package com.masphe.accessgithub.ui.users.model

class Bean {
    data class Users(
        val users: MutableList<User>
    )

    data class User(
        val avatar_url: String,
        val login: String,
        val site_admin: Boolean
    )
}
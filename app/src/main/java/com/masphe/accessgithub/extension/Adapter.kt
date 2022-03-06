package com.masphe.accessgithub.extension

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.masphe.accessgithub.dataCenter.api.response.User
import com.masphe.accessgithub.ui.users.view.UsersAdapter

object Adapter{
    @JvmStatic
    @BindingAdapter("isStaff")
    fun TextView.setStaff(isStaff: Boolean){
        if (isStaff) this.text = "Staff"
        else this.visibility = View.GONE
    }
}
package com.masphe.accessgithub.extension

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.masphe.accessgithub.ui.users.model.Bean
import com.masphe.accessgithub.ui.users.view.UsersAdapter

object Adapter{
    @JvmStatic
    @BindingAdapter("data", "listener")
    fun RecyclerView.setUsers(data: Bean.Users?, listener: UsersAdapter.OnItemClickListener){
        if (data != null) this.adapter = UsersAdapter(data!!, listener)
    }

    @JvmStatic
    @BindingAdapter("isStaff")
    fun TextView.setStaff(isStaff: Boolean){
        if (isStaff) this.text = "Staff"
        else this.visibility = View.GONE
    }
}
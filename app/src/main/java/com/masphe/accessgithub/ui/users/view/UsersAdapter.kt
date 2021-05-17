package com.masphe.accessgithub.ui.users.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.masphe.accessgithub.BR
import com.masphe.accessgithub.R
import com.masphe.accessgithub.ui.users.model.Bean

class UsersAdapter(private val data: Bean.Users,  private val listener: OnItemClickListener): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    interface OnItemClickListener{
        fun onItemClick(userName: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ViewHolderStandard(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_user, parent, false))

    override fun getItemCount(): Int = this.data.users.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as ViewHolderStandard).binding
        binding.setVariable(BR.user, this.data.users[position])
        binding.root.setOnClickListener {
            this.listener.onItemClick(this.data.users[position].login)
        }
    }

    private class ViewHolderStandard(val binding: ViewDataBinding): RecyclerView.ViewHolder(binding.root)
}
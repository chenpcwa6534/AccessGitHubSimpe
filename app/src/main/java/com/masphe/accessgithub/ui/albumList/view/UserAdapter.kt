package com.masphe.accessgithub.ui.albumList.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.masphe.accessgithub.BR
import com.masphe.accessgithub.R
import com.masphe.accessgithub.dataCenter.api.response.User

class UserAdapter(private val users: MutableList<User>, val onItemClickListener: OnItemClickListener): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var onSelectedIndex = 0

    interface OnItemClickListener{
        fun onClickUser(userId: User)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ViewHolderStandard(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_users, parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as ViewHolderStandard).binding
        binding.setVariable(BR.user, this.users[position])
        binding.setVariable(BR.backgroundImageId, position)
        binding.root.setOnClickListener {
            updateSelectedStatus(position, this.onSelectedIndex)
            this.onSelectedIndex = holder.adapterPosition
            this.onItemClickListener.onClickUser(this.users[position])
        }
    }

    override fun getItemCount(): Int = users.size

    private fun updateSelectedStatus(nowIndex: Int, oldIndex: Int){
        this.users[oldIndex].isSelected = false
        notifyItemChanged(oldIndex)
        this.users[nowIndex].isSelected = true
        notifyItemChanged(nowIndex)
    }

    private class ViewHolderStandard(val binding: ViewDataBinding): RecyclerView.ViewHolder(binding.root)
}
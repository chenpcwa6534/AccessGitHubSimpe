package com.masphe.accessgithub.ui.users.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.masphe.accessgithub.R
import com.masphe.accessgithub.dataCenter.api.response.User
import com.masphe.accessgithub.extension.Image.setRoundImage
import com.masphe.accessgithub.widgets.LoadOnRecyclerOnScrollListener

class UsersAdapter(private val data: MutableList<User>,  private val listener: OnItemClickListener): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private val TYPE_ITEM = 1
    private val TYPE_FOOTER = 2

    private val footerItemSize = 1

    private var loadState = LoadState.LOADING_COMPLETED

    val onScrollListener: LoadOnRecyclerOnScrollListener by lazy {
        object : LoadOnRecyclerOnScrollListener(){
            override fun onLoad() {
                this@UsersAdapter.loadState = LoadState.LOADING
                this@UsersAdapter.listener.onLoad()
                notifyItemChanged(this@UsersAdapter.data.size)
            }
        }
    }

    enum class LoadState(){
        LOADING, LOADING_COMPLETED, LOADING_END
    }

    interface OnItemClickListener{
        fun onItemClick(userName: String?, view: View)
        fun onLoad()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when(viewType){
            this.TYPE_FOOTER -> ViewHolderFooter(LayoutInflater.from(parent.context).inflate(R.layout.item_refresh_footer, parent, false))
            else -> ViewHolderStandard(LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false))
        }

    override fun getItemCount(): Int = this.data.size + footerItemSize

    override fun getItemViewType(position: Int): Int =
        when(position + footerItemSize) {
            itemCount -> this.TYPE_FOOTER
            else -> this.TYPE_ITEM
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is ViewHolderStandard -> bindViewHolderStandard(holder, position)
            is ViewHolderFooter -> bindViewHolderFooter(holder)
        }
    }

    fun addItemData(isEnd: Boolean, users: MutableList<User>? = null){
        if (isEnd){
            this.loadState = LoadState.LOADING_END
        }else{
            users?.let {
                this.data.addAll(users)
                this.loadState = LoadState.LOADING_COMPLETED
            }
        }
        notifyDataSetChanged()
    }

    private fun bindViewHolderStandard(holder: ViewHolderStandard, position: Int){
        holder.let {
            it.setData(this.data[position], position)
            it.itemView.setOnClickListener {
                this.listener.onItemClick(this.data[position].login, holder.itemView)
            }
        }
    }

    private fun bindViewHolderFooter(holder: ViewHolderFooter){
        holder.setState(this.loadState)
    }

    private class ViewHolderStandard(itemView: View): RecyclerView.ViewHolder(itemView){
        private val picture = itemView.findViewById<ImageView>(R.id.img_picture)
        private val name = itemView.findViewById<TextView>(R.id.txt_name)
        private val number = itemView.findViewById<TextView>(R.id.txt_number)
        private val staff = itemView.findViewById<TextView>(R.id.txt_staff)

        fun setData(user: User, position: Int){
            picture.setRoundImage(user.avatar_url)
            name.text = user.login
            number.text = "${position + 1}"

            if (user.site_admin) staff.text = "Staff"
            else staff.visibility = View.GONE
        }
    }

    private class ViewHolderFooter(itemView: View): RecyclerView.ViewHolder(itemView){
        private val loadingProgress = itemView.findViewById<ProgressBar>(R.id.pg_loading)
        private val loadingMsg = itemView.findViewById<TextView>(R.id.txt_loading_msg)

        fun setState(state: LoadState){
            when(state){
                LoadState.LOADING -> displayOfLoading()
                LoadState.LOADING_COMPLETED -> displayOfLoadingCompleted()
                LoadState.LOADING_END -> displayOfLoadingEnd()
            }
        }

        private fun displayOfLoading(){
            this.loadingProgress.visibility = View.VISIBLE
            this.loadingMsg.text = itemView.context.getString(R.string.loading)
            this.loadingMsg.visibility = View.VISIBLE
        }

        private fun displayOfLoadingCompleted(){
            this.loadingProgress.visibility = View.GONE
            this.loadingMsg.visibility = View.GONE
        }

        private fun displayOfLoadingEnd(){
            this.loadingProgress.visibility = View.GONE
            this.loadingMsg.text = itemView.context.getString(R.string.loading_end)
            this.loadingMsg.visibility = View.VISIBLE
        }
    }
}
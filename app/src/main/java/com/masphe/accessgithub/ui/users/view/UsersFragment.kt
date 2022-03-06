package com.masphe.accessgithub.ui.users.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.masphe.accessgithub.R
import com.masphe.accessgithub.dataCenter.api.response.User
import com.masphe.accessgithub.ui.Contract
import com.masphe.accessgithub.ui.base.BaseFragment
import com.masphe.accessgithub.ui.users.presenter.IPresenter
import com.masphe.lib.arch.components.LayoutId
import kotlinx.android.synthetic.main.fragment_users.*
import kotlinx.android.synthetic.main.fragment_users.toolbar
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

@LayoutId(R.layout.fragment_users)
class UsersFragment: BaseFragment(), IView, UsersAdapter.OnItemClickListener {

    private val TAG = "Users Fragment"

    val presenter: IPresenter by inject { parametersOf(this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        swipe_users.setOnRefreshListener {
            this.presenter.onLoadUser()
        }
        this.presenter.onLoadUser()
        this.loadingDialog.show()
    }

    override fun updateUsers(users: MutableList<User>) {
        swipe_users.isRefreshing = false
        users?.let {
            list_users.adapter = UsersAdapter(users, this)
            list_users.addOnScrollListener((list_users.adapter as UsersAdapter).onScrollListener!!)
        }
        this.loadingDialog.dismiss()
    }

    override fun addUsers(isEnd: Boolean, users: MutableList<User>?) {
        (list_users.adapter as UsersAdapter).addItemData(isEnd, users)
    }

    override fun onShowErrorDialog(msg: String) {
        swipe_users.isRefreshing = false
        this.loadingDialog.dismiss()
        val dialog = this.errorAlertDialog
        dialog.setMessage(msg)
        dialog.show()
    }

    override fun onItemClick(userName: String?, view: View) {
        userName?.let {
            val bundle = Bundle()
            bundle.putString(Contract.KEY_USER_NAME, userName)

            transFragment(R.id.action_usersFragment_to_userFragment, bundle)
        }?: Toast.makeText(
                this.requireContext(),
                this.requireContext().getString(R.string.error_client_no_data),
                Toast.LENGTH_SHORT)
            .show()
    }

    override fun onLoad() {
        this.presenter.onLoadOnNextPage()
    }
}
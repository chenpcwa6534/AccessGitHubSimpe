package com.masphe.accessgithub.ui.user.view

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import com.masphe.accessgithub.R
import com.masphe.accessgithub.dataCenter.api.response.User
import com.masphe.accessgithub.extension.Adapter.setStaff
import com.masphe.accessgithub.extension.Image.setRoundImage
import com.masphe.accessgithub.ui.Contract
import com.masphe.accessgithub.ui.base.BaseFragment
import com.masphe.accessgithub.ui.user.presenter.IPresenter
import com.masphe.lib.arch.components.LayoutId
import kotlinx.android.synthetic.main.fragment_user.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

@LayoutId(R.layout.fragment_user)
class UserFragment: BaseFragment(), IView{
    private val TAG = "User Fragment"

    val presenter: IPresenter by inject { parametersOf(this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ViewCompat.setTransitionName(img_picture, "img_picture")

        setHasOptionsMenu(true)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar!!.setDisplayShowHomeEnabled(true)
        this.requireArguments().getString(Contract.KEY_USER_NAME)?.let {
            this.loadingDialog.show()
            this.presenter.getUserInfo(it)
        } ?: showUserNameNullError()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item!!.itemId){
            android.R.id.home -> this.popBackFragment()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun updateUserInfo(user: User) {
        img_picture.setRoundImage(user.avatar_url)
        txt_name.text = user.name
        txt_bio.text = user.bio
        txt_login.text = user.login
        txt_admin.setStaff(user.site_admin)
        txt_location.text = user.location
        txt_link.text = user.blog
        this.loadingDialog.dismiss()
    }

    override fun onShowErrorDialog(msg: String) {
        val dialog = this.errorAlertDialog
        dialog.setMessage(msg)
        dialog.show()
    }

    override fun showUserNameNullError(){
        AlertDialog.Builder(this.requireContext())
            .setTitle("Oops...")
            .setMessage(this.requireContext().getString(R.string.error_client_no_data))
            .setPositiveButton("Ok") { dialog, _ ->
                dialog.dismiss()
                this.popBackFragment()
            }
            .show()
    }
}
package com.masphe.accessgithub.ui.user.view

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.masphe.accessgithub.BR
import com.masphe.accessgithub.R
import com.masphe.accessgithub.ui.base.BaseFragment
import com.masphe.accessgithub.ui.user.vm.ViewModel
import com.masphe.lib.arch.components.LayoutId
import kotlinx.android.synthetic.main.fragment_user.*
import org.koin.android.viewmodel.ext.android.viewModel

@LayoutId(R.layout.fragment_user)
class UserFragment: BaseFragment(){
    private val TAG = "User Fragment"

    private val viewModel by viewModel<ViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.binding.setVariable(BR.state, this.viewModel.getUIState())
        this.viewModel.getBundle(this.arguments)

        setHasOptionsMenu(true)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar!!.setDisplayShowHomeEnabled(true)
        onUIChangeListener()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item!!.itemId){
            android.R.id.home -> this.popBackFragment()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun onUIChangeListener(){
        this.viewModel.getUIState().isShowProgress.observe(viewLifecycleOwner, Observer {
            if (it) progressDialog?.show()
            else progressDialog?.dismiss()
        })

        this.viewModel.getUIState().isShowErrorDialog.observe(viewLifecycleOwner, Observer {
            showApiErrorDialog(it)
        })
    }
}
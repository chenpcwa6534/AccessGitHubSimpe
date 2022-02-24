package com.masphe.accessgithub.ui.users.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.masphe.accessgithub.BR
import com.masphe.accessgithub.R
import com.masphe.accessgithub.ui.base.BaseFragment
import com.masphe.accessgithub.ui.users.vm.ViewModel
import com.masphe.lib.arch.components.LayoutId
import org.koin.android.viewmodel.ext.android.viewModel

@LayoutId(R.layout.fragment_users)
class UsersFragment: BaseFragment(){
    private val TAG = "Other Fragment"

    private val viewModel by viewModel<ViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.binding.setVariable(BR.state, this.viewModel.getUIState())
        onUIChangeListener()
    }

    private fun onUIChangeListener(){
        this.viewModel.getUIState().isShowProgress.observe(viewLifecycleOwner, Observer {
            if (it) progressDialog?.show()
            else progressDialog?.dismiss()
        })

        this.viewModel.getUIState().isShowErrorDialog.observe(viewLifecycleOwner, Observer {
            showApiErrorDialog(it)
        })

        this.viewModel.getUIState().pageTrans.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                transFragment(R.id.action_usersFragment_to_userFragment, it)
                this.viewModel.getUIState().pageTrans.postValue(null)
            }
        })
    }
}
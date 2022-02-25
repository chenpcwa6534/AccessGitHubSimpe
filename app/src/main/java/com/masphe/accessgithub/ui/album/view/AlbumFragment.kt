package com.masphe.accessgithub.ui.album.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.masphe.accessgithub.BR
import com.masphe.accessgithub.R
import com.masphe.accessgithub.ui.album.vm.ViewModel
import com.masphe.accessgithub.ui.base.BaseFragment
import com.masphe.lib.arch.components.LayoutId
import org.koin.android.viewmodel.ext.android.viewModel


@LayoutId(R.layout.fragment_album)
class AlbumFragment: BaseFragment(){
    private val TAG = "Album List Fragment"

    private val viewModel by viewModel<ViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.binding.setVariable(BR.state, this.viewModel.uiState)
        this.uiState = this.viewModel.uiState
        onUIChangeListener()
    }

    private fun onUIChangeListener(){
        this.viewModel.uiState.pageTrans.observe(viewLifecycleOwner, Observer {
            it?.let {
                transFragment(R.id.action_albumListFragment_to_albumFragment, it)
                this.viewModel.uiState.pageTrans.postValue(null)
            }
        })
    }
}
package com.masphe.accessgithub.ui.album.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.masphe.accessgithub.BR
import com.masphe.accessgithub.R
import com.masphe.accessgithub.ui.album.vm.ViewModel
import com.masphe.accessgithub.ui.base.BaseFragment
import com.masphe.lib.arch.components.LayoutId
import kotlinx.android.synthetic.main.fragment_album.*
import org.koin.android.viewmodel.ext.android.viewModel

@LayoutId(R.layout.fragment_album)
class AlbumFragment: BaseFragment() {
    private val TAG = "Album Fragment"

    private val viewModel by viewModel<ViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.binding.setVariable(BR.state, this.viewModel.uiState)
        this.viewModel.getBundle(this.arguments)
        (this.activity as AppCompatActivity).setSupportActionBar(toolbar)
    }

    private fun asd(){

    }
}
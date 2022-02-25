package com.masphe.accessgithub.ui.gallery.view

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import cc.shinichi.library.ImagePreview
import cc.shinichi.library.bean.ImageInfo
import com.masphe.accessgithub.BR
import com.masphe.accessgithub.R
import com.masphe.accessgithub.dataCenter.api.response.Photo
import com.masphe.accessgithub.ui.gallery.vm.ViewModel
import com.masphe.accessgithub.ui.base.BaseFragment
import com.masphe.lib.arch.components.LayoutId
import kotlinx.android.synthetic.main.fragment_gallery.*
import org.koin.android.viewmodel.ext.android.viewModel

@LayoutId(R.layout.fragment_gallery)
class GalleryFragment: BaseFragment() {
    private val TAG = "Album Fragment"

    private val viewModel by viewModel<ViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.binding.setVariable(BR.state, this.viewModel.uiState)
        this.viewModel.getBundle(this.arguments)

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbar.setNavigationOnClickListener {
            this.requireActivity().onBackPressed()
        }

        onUIChangeListener()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item!!.itemId){
            android.R.id.home -> this.popBackFragment()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun onUIChangeListener(){
        this.viewModel.uiState.onShowPicture.observe(viewLifecycleOwner, Observer {
            showPictureView(it, this.viewModel.uiState.album.value!!.photo)
        })
    }

    private fun showPictureView(photoIndex: Int, photos: MutableList<Photo>){
        ImagePreview.getInstance()
            .setContext(this.requireActivity())
            .setImageInfoList(getImageInfo(photos))
            .setIndex(photoIndex)
            .setEnableDragClose(true)
            .setShowIndicator(true)
            .setErrorPlaceHolder(R.mipmap.img_loading_error_placeholder)
            .start()
    }

    private fun getImageInfo(photos: MutableList<Photo>): MutableList<ImageInfo>{
        val imageInfoList = mutableListOf<ImageInfo>()
        photos.forEach {
            val imageInfo = ImageInfo()
            imageInfo.originUrl = it.url
            imageInfo.thumbnailUrl = it.thumbnailUrl
            imageInfoList.add(imageInfo)
        }
        return imageInfoList
    }
}
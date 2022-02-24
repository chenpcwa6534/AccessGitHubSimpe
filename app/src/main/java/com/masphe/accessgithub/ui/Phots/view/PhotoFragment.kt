package com.masphe.accessgithub.ui.Phots.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.masphe.accessgithub.R
import com.masphe.accessgithub.dataCenter.api.response.Album
import com.masphe.accessgithub.ui.Contract
import com.masphe.accessgithub.ui.base.BaseFragment
import com.masphe.lib.arch.components.LayoutId
import kotlinx.android.synthetic.main.fragment_photo.*

@LayoutId(R.layout.fragment_photo)
class PhotoFragment: BaseFragment() {

    private var index: Int = 0
    private lateinit var album: Album

    companion object{
        fun getInstance(index: Int, album: Album): Fragment {
            val bundle = Bundle()
            bundle.putInt("index", index)
            bundle.putParcelable(Contract.KeyName.Album, album)
            val fragment = PhotoFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.arguments?.let {
            this.index = it.getInt("index")
            this.album = it.getParcelable<Album>(Contract.KeyName.Album)!!
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        img_picture.setOnClickListener {
            this.activity?.supportFinishAfterTransition()
        }

        img_picture.viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener{
            override fun onPreDraw(): Boolean {
                img_picture.viewTreeObserver.removeOnPreDrawListener(this)
                this@PhotoFragment.activity?.supportPostponeEnterTransition()
                return true
            }
        })
    }

    override fun onResume() {
        super.onResume()
        Glide.with(this.requireContext())
            .load(this.album.photo[this.index].url)
            .dontAnimate()
            .into(img_picture)
    }

    fun getSharedElement(): View = img_picture

}
package com.masphe.accessgithub.ui.Phots.view

import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.masphe.accessgithub.R
import com.masphe.accessgithub.dataCenter.api.response.Album
import com.masphe.accessgithub.ui.Contract
import com.masphe.accessgithub.ui.base.BaseActivity
import com.masphe.lib.arch.components.LayoutId

@LayoutId(R.layout.activity_photo)
class PhotoActivity: BaseActivity() {

    private lateinit var viewPage: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.viewPage = this.findViewById(R.id.viewpager)
        supportPostponeEnterTransition()

        val album = this.intent.getParcelableExtra<Album>(Contract.KeyName.Album)

        viewPage.adapter = PhotoPageAdapter(album, supportFragmentManager)

    }
}
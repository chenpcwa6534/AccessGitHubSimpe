package com.masphe.accessgithub.ui.Phots.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.masphe.accessgithub.dataCenter.api.response.Album

class PhotoPageAdapter constructor(val album: Album, val fm: FragmentManager) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return PhotoFragment.getInstance(
            position, album
        )
    }

    override fun getCount(): Int = this.album.photo.size
}
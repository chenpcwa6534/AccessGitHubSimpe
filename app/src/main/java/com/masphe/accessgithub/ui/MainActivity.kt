package com.masphe.accessgithub.ui

import android.os.Bundle
import com.masphe.accessgithub.R
import com.masphe.accessgithub.ui.base.BaseActivity
import com.masphe.lib.arch.components.LayoutId
import com.masphe.lib.arch.components.NavHostId

@LayoutId(R.layout.activity_main)
@NavHostId(R.id.albumListFragment)
class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

}
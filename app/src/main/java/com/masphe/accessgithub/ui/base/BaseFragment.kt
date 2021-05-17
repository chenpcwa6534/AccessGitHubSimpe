package com.masphe.accessgithub.ui.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.masphe.accessgithub.widgets.LoadingDialog
import com.masphe.lib.arch.rigger.FragmentRigger

open class BaseFragment: FragmentRigger(){
    val NotTransPage = -1
    var progressDialog: LoadingDialog? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.progressDialog = LoadingDialog(context)
    }

    fun showApiErrorDialog(msg: String){
        AlertDialog.Builder(this.requireContext())
            .setTitle("Oops...")
            .setMessage(msg)
            .setPositiveButton("Ok", null)
            .show()
    }
}
package com.masphe.accessgithub.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.masphe.accessgithub.R
import com.masphe.accessgithub.widgets.LoadingDialog
import com.masphe.lib.arch.rigger.FragmentRigger

open class BaseFragment: FragmentRigger(){
    var progressDialog: LoadingDialog? = null

    private val errorAlertDialog by lazy {
        AlertDialog.Builder(this.requireContext())
            .setTitle("Oops...")
            .setPositiveButton("Ok", null)
            .create()
    }

    private val loadingDialog by lazy {
        val loadView = LayoutInflater.from(context).inflate(R.layout.dialog_loading, null)

        val tvTip: TextView = loadView.findViewById(R.id.tvTip)
        tvTip.text = "Loading..."
        AlertDialog.Builder(this.requireContext(), R.style.CustomProgressDialog)
            .setView(loadView)
            .setCancelable(false)
            .create()
    }

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
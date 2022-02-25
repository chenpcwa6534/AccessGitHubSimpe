package com.masphe.accessgithub.ui.base

import android.content.DialogInterface
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import com.masphe.accessgithub.R
import com.masphe.lib.arch.rigger.FragmentRigger

open class BaseFragment: FragmentRigger(){

    var uiState: BaseUiState? = null
        set(value) {
            field = value
            onUIChangeListener()
        }

    private val errorAlertDialog by lazy {
        this.uiState?.let {
            AlertDialog.Builder(this.requireContext())
                .setTitle("Oops...")
                .setMessage(this.uiState!!.errorDialogMsg.value)
                .setPositiveButton("Ok", object : DialogInterface.OnClickListener{
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        this@BaseFragment.uiState!!.isShowErrorDialog.postValue(false)
                    }
                })
                .create()
        }
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

    private fun onUIChangeListener(){
        this.uiState?.let {
            this.uiState!!.isShowErrorDialog.observe(viewLifecycleOwner, Observer {
                if (it) this.errorAlertDialog!!.show()
                else this.errorAlertDialog!!.dismiss()
            })

            this.uiState!!.isShowProgress.observe(viewLifecycleOwner, Observer {
                if (it) this.loadingDialog.show()
                else  {
                    if (this.loadingDialog.isShowing) this.loadingDialog.dismiss()
                }
            })
        }
    }
}
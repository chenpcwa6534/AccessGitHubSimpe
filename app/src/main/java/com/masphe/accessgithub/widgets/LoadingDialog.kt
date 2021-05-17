package com.masphe.accessgithub.widgets

import android.content.Context
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.masphe.accessgithub.R


class LoadingDialog constructor(private val context: Context?){
    private var alertDialog: AlertDialog = AlertDialog.Builder(context!!, R.style.CustomProgressDialog).create()

    fun show(){
        val loadView = LayoutInflater.from(context).inflate(R.layout.dialog_loading, null)
        this.alertDialog.setView(loadView, 0, 0, 0, 0)
        this.alertDialog.setCanceledOnTouchOutside(false)

        val tvTip: TextView = loadView.findViewById(R.id.tvTip)
        tvTip.text = "Loading..."

        this.alertDialog.show()
    }

    fun dismiss() {
        if (this.alertDialog != null && this.alertDialog.isShowing()) {
            this.alertDialog.dismiss()
        }
    }
}
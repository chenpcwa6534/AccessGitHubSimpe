package com.masphe.lib.arch.rigger

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.masphe.lib.arch.components.AnnotationParse
import java.lang.RuntimeException

open class ActivityRigger : AppCompatActivity() {
    private val TAG = "Activity Rigger"

//    val binding by lazy {
//        val viewDataBinding = DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(this), AnnotationParse.getAnnotatedLayout(this), null, false)
//        viewDataBinding.lifecycleOwner = this
//        viewDataBinding
//    }
    var nav: NavController? = null
    override fun onBackPressed() {
        super.onBackPressed()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(AnnotationParse.getAnnotatedLayout(this))
//        this.setContentView(this.binding.root)
    }

    fun transActivity(id: Int, args: Bundle? = null){
        try {

            val navHostId = AnnotationParse.getAnnotatedNavHost(this)
            Navigation.findNavController(this, navHostId).navigate(id, args)
        }catch (e: RuntimeException){
            Log.e(TAG, "Can't not find navhostFragment ${e.message}")
        }
    }

    fun popBackFragment(){
        this.supportFragmentManager.popBackStack()
    }
}
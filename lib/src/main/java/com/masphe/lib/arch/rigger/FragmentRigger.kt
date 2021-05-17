package com.masphe.lib.arch.rigger

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment
import com.masphe.lib.arch.components.AnnotationParse

open class FragmentRigger :Fragment(){
    private val TAG = "Fragment Riggrt"

    val binding by lazy {
        val viewDataBinding = DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(this.context), AnnotationParse.getAnnotatedLayout(this), null, false)
        viewDataBinding.lifecycleOwner = this
        viewDataBinding
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = this.binding.root

    fun transFragment(id: Int){ NavHostFragment.findNavController(this).navigate(id) }
    fun transFragment(id: Int, bundle: Bundle){ NavHostFragment.findNavController(this).navigate(id, bundle) }
    fun transFragment(node: NavDirections){ NavHostFragment.findNavController(this).navigate(node) }
    fun transActivity(id: Int, args: Bundle? = null){ (this.context as ActivityRigger).transActivity(id, args)}
    fun popBackFragment(){ NavHostFragment.findNavController(this).popBackStack() }
}
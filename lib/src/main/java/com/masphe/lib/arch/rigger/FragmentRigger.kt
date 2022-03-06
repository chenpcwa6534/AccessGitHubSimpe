package com.masphe.lib.arch.rigger

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment
import com.masphe.lib.arch.components.AnnotationParse

open class FragmentRigger :Fragment(){
    private val TAG = "Fragment Rigger"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(AnnotationParse.getAnnotatedLayout(this), null)

    fun transFragment(id: Int, bundle: Bundle? = null){ NavHostFragment.findNavController(this).navigate(id, bundle) }
    fun transFragment(node: NavDirections){ NavHostFragment.findNavController(this).navigate(node) }
    fun transActivity(id: Int, args: Bundle? = null){ (this.context as ActivityRigger).transActivity(id, args) }
    fun popBackFragment(){ NavHostFragment.findNavController(this).popBackStack() }
}
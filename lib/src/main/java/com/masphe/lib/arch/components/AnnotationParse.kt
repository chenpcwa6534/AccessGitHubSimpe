package com.masphe.lib.arch.components

import java.lang.RuntimeException

object AnnotationParse{
    fun getAnnotatedLayout(theObject: Any): Int =
        try {
            Class.forName(theObject.javaClass.name).getAnnotation(LayoutId::class.java).value
        }catch (e: ClassNotFoundException){
            e.printStackTrace()
            throw RuntimeException("No annotated layout.")
        }

    fun getAnnotatedNavHost(theObject: Any): Int =
        try {
            Class.forName(theObject.javaClass.name).getAnnotation(NavHostId::class.java).value
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
            throw RuntimeException("No nav host fragment layout")
        }
}
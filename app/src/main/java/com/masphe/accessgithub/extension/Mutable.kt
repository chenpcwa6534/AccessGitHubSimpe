package com.masphe.accessgithub.extension

import androidx.lifecycle.MutableLiveData

/** Mutable Live Data */
fun <T: Any?> MutableLiveData<T>.default(value: T) = apply { setValue(value) }

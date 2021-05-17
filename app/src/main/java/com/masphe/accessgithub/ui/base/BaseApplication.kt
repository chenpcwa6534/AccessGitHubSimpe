package com.masphe.accessgithub.ui.base

import android.app.Application
import com.masphe.accessgithub.di.retrofitClient
import com.masphe.accessgithub.di.userViewModelScope
import com.masphe.accessgithub.di.usersViewModelScope
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module

class BaseApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        configureKoin()
    }

    private fun configureKoin(){
        startKoin {
            androidContext(this@BaseApplication)
            modules(getKoinModules())
        }
    }

    private fun getKoinModules(): MutableList<Module>{
        val modules = mutableListOf<Module>()
        //Api Module
        modules.add(retrofitClient)

        //UI Module
        modules.add(usersViewModelScope)
        modules.add(userViewModelScope)

        return modules
    }
}
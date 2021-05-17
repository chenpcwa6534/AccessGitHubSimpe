package com.masphe.accessgithub.di

import com.masphe.accessgithub.dataCenter.Repository
import com.masphe.accessgithub.ui.user.view.UserFragment
import org.koin.androidx.fragment.dsl.fragment
import org.koin.dsl.module

import com.masphe.accessgithub.ui.users.model.Model as UsersModel
import com.masphe.accessgithub.ui.users.vm.ViewModel as UsersViewModel

import com.masphe.accessgithub.ui.user.model.Model as UserModel
import com.masphe.accessgithub.ui.user.vm.ViewModel as UserViewModel

val usersViewModelScope = module {
    fun provideUsersModel(repository: Repository): UsersModel = UsersModel.getInstance(repository)

    single { provideUsersModel(get()) }
    factory { UsersViewModel.getInstance(get()) }
}

val userViewModelScope = module {
    fun provideUserModel(repository: Repository): UserModel = UserModel.getInstance(repository)

    single { provideUserModel(get()) }
    factory { UserViewModel.getInstance(get()) }
}
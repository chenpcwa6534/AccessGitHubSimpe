package com.masphe.accessgithub.di

import android.content.Context
import com.masphe.accessgithub.dataCenter.Repository
import org.koin.dsl.module

import com.masphe.accessgithub.ui.users.view.IView as IViewOfUsers
import com.masphe.accessgithub.ui.users.model.IModel as IModelOfUsers
import com.masphe.accessgithub.ui.users.presenter.IPresenter as IPresenterOfUsers
import com.masphe.accessgithub.ui.users.model.Model as ModelOfUsers
import com.masphe.accessgithub.ui.users.presenter.Presenter as ViewModelOfUsers

import com.masphe.accessgithub.ui.user.view.IView as IViewOfUser
import com.masphe.accessgithub.ui.user.model.IModel as IModelOfUser
import com.masphe.accessgithub.ui.user.presenter.IPresenter as IPresenterOfUser
import com.masphe.accessgithub.ui.user.model.Model as ModelOfUser
import com.masphe.accessgithub.ui.user.presenter.Presenter as ViewModelOfUser

val usersViewModelScope = module {
    fun provideUsersModel(context: Context, repository: Repository): IModelOfUsers = ModelOfUsers.getInstance(context, repository)
    fun providePresenter(view: IViewOfUsers, model: IModelOfUsers): IPresenterOfUsers = ViewModelOfUsers.getInstance(view, model)

    single { provideUsersModel(get(), get()) }
    factory { (view: IViewOfUsers) -> providePresenter(view, get()) }
}

val userViewModelScope = module {
    fun provideUserModel(context: Context, repository: Repository): IModelOfUser = ModelOfUser.getInstance(context, repository)
    fun providePresenter(view: IViewOfUser, model: IModelOfUser): IPresenterOfUser = ViewModelOfUser.getInstance(view, model)

    single { provideUserModel(get(), get()) }
    factory { (view: IViewOfUser) -> providePresenter(view, get())}
}
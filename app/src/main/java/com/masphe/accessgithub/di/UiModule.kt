package com.masphe.accessgithub.di

import com.masphe.accessgithub.dataCenter.Repository
import org.koin.dsl.module

import com.masphe.accessgithub.ui.albumList.model.Model as AlbumListModel
import com.masphe.accessgithub.ui.albumList.vm.ViewModel as AlbumListViewModel
import com.masphe.accessgithub.ui.albumList.vm.UIState as AlbumListUIState

import com.masphe.accessgithub.ui.album.model.Model as AlbumModel
import com.masphe.accessgithub.ui.album.vm.UIState as AlbumUIState
import com.masphe.accessgithub.ui.album.vm.ViewModel as AlbumViewModel

//val usersViewModelScope = module {
//    fun provideUsersModel(repository: Repository): UsersModel = UsersModel.getInstance(repository)
//
//    single { provideUsersModel(get()) }
//    factory { UsersViewModel.getInstance(get()) }
//}
//
//val userViewModelScope = module {
//    fun provideUserModel(repository: Repository): UserModel = UserModel.getInstance(repository)
//
//    single { provideUserModel(get()) }
//    factory { UserViewModel.getInstance(get()) }
//}

val albumListViewModelScope = module {
    fun provideAlbumListModel(repository: Repository): AlbumListModel = AlbumListModel.getInstance(repository)
    fun provideAlbumListUIState(): AlbumListUIState = AlbumListUIState()

    single { provideAlbumListModel(get()) }
    single { provideAlbumListUIState() }

    factory { AlbumListViewModel.getInstance(get(), get()) }
}

val albumViewModelScope = module {
    fun provideAlbumModel(repository: Repository): AlbumModel = AlbumModel.getInstance(repository)
    fun provideAlbumUIState(): AlbumUIState = AlbumUIState()

    single { provideAlbumModel(get()) }
    single { provideAlbumUIState() }

    factory { AlbumViewModel.getInstance(get(), get()) }
}
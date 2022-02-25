package com.masphe.accessgithub.di

import com.masphe.accessgithub.dataCenter.Repository

import org.koin.dsl.module

import com.masphe.accessgithub.ui.album.model.Model as AlbumModel
import com.masphe.accessgithub.ui.album.vm.ViewModel as AlbumViewModel
import com.masphe.accessgithub.ui.album.vm.UIState as AlbumUIState

import com.masphe.accessgithub.ui.gallery.vm.UIState as GalleryUIState
import com.masphe.accessgithub.ui.gallery.vm.ViewModel as GalleryViewModel

val albumListViewModelScope = module {
    fun provideAlbumModel(repository: Repository): AlbumModel = AlbumModel.getInstance(repository)
    fun provideAlbumUIState(): AlbumUIState = AlbumUIState()

    single { provideAlbumModel(get()) }
    single { provideAlbumUIState() }

    factory { AlbumViewModel.getInstance(get(), get()) }
}

val albumViewModelScope = module {
    fun provideGalleryUIState(): GalleryUIState = GalleryUIState()

    single { provideGalleryUIState() }

    factory { GalleryViewModel.getInstance(get()) }
}


package com.serhankhan.posts.di.module

import androidx.lifecycle.ViewModelProvider
import com.serhankhan.posts.viewmodels.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(modelProviderFactory:ViewModelProviderFactory):ViewModelProvider.Factory

}
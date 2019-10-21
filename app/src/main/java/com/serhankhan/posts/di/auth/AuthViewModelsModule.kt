package com.serhankhan.posts.di.auth

import androidx.lifecycle.ViewModel
import com.serhankhan.posts.di.view_model_utils.ViewModelKey
import com.serhankhan.posts.viewmodels.auth.AuthViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AuthViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    abstract fun bindAuthViewModel(authViewModel: AuthViewModel):ViewModel

}
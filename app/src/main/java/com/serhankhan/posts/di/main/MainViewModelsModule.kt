package com.serhankhan.posts.di.main

import androidx.lifecycle.ViewModel
import com.serhankhan.posts.di.view_model_utils.ViewModelKey
import com.serhankhan.posts.viewmodels.main.post.PostViewModel
import com.serhankhan.posts.viewmodels.main.profile.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindProfileViewModel(viewModel: ProfileViewModel):ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PostViewModel::class)
    abstract fun bindPostViewModel(viewModel:PostViewModel):ViewModel

}
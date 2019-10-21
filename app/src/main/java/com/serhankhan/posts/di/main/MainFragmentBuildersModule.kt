package com.serhankhan.posts.di.main

import com.serhankhan.posts.ui.main.post.PostFragment
import com.serhankhan.posts.ui.main.profile.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeProfileFragment(): ProfileFragment


    @ContributesAndroidInjector
    abstract fun contributePostsFragment():PostFragment
}
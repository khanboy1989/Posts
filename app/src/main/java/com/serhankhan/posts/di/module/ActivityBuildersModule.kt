package com.serhankhan.posts.di.module

import com.serhankhan.posts.di.auth.AuthModule
import com.serhankhan.posts.di.auth.AuthScope
import com.serhankhan.posts.di.auth.AuthViewModelsModule
import com.serhankhan.posts.di.main.MainFragmentBuildersModule
import com.serhankhan.posts.di.main.MainModule
import com.serhankhan.posts.di.main.MainScope
import com.serhankhan.posts.di.main.MainViewModelsModule
import com.serhankhan.posts.ui.auth.AuthActivity
import com.serhankhan.posts.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @MainScope
    @ContributesAndroidInjector(modules = [MainFragmentBuildersModule::class, MainViewModelsModule::class,MainModule::class])
    abstract fun contributeMainActivity(): MainActivity


    @AuthScope
    @ContributesAndroidInjector(modules = [AuthViewModelsModule::class, AuthModule::class])
    abstract fun contributeAuthActivity(): AuthActivity



}
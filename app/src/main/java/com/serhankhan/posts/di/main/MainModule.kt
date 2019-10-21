package com.serhankhan.posts.di.main

import com.serhankhan.posts.remote_data_manager.main.MainApi
import com.serhankhan.posts.ui.main.post.PostsRecyclerAdapter
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MainModule {


    @Module
    companion object {
        @Provides
        @JvmStatic
        @MainScope
        fun providesRecyclerAdapter():PostsRecyclerAdapter{
            return PostsRecyclerAdapter()
        }

        @Provides
        @JvmStatic
        @MainScope
        fun provideMainApi(retrofit:Retrofit):MainApi {
            return retrofit.create(MainApi::class.java)
        }
    }




}
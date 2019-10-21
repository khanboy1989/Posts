package com.serhankhan.posts.di.auth

import com.serhankhan.posts.remote_data_manager.auth.AuthApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
abstract class AuthModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        @AuthScope
        fun provideAuthApi(retrofit: Retrofit):AuthApi {
            return retrofit.create(AuthApi::class.java)
        }
    }


}
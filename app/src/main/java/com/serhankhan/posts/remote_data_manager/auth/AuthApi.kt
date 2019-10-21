package com.serhankhan.posts.remote_data_manager.auth

import com.serhankhan.posts.model.auth.User
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface AuthApi {

    @GET("users/{id}")
    fun getUser(@Path("id") id:Int): Single<User>



}
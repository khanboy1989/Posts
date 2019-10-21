package com.serhankhan.posts.remote_data_manager.main

import com.serhankhan.posts.model.post.Post
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MainApi {

    // /posts?userId=1
    @GET("posts")
    fun getPostsFromUser(@Query("userId") id:Int): Single<List<Post>>

}
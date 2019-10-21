package com.serhankhan.posts.viewmodels.main.post

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.serhankhan.posts.SessionManager
import com.serhankhan.posts.model.post.Post
import com.serhankhan.posts.remote_data_manager.main.MainApi
import com.serhankhan.posts.ui.main.PostResource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostViewModel @Inject constructor(var mainApi:MainApi, var sessionManager:SessionManager): ViewModel() {


    private var TAG:String = PostViewModel::class.java.simpleName

    private lateinit var posts:MutableLiveData<PostResource<List<Post>>>

    fun getPosts(){
        Log.d(TAG,"View model injected to PostFragment")
        getPostsForUser()
    }

    @SuppressLint("CheckResult")
    private fun getPostsForUser(){
        posts = MutableLiveData()
        posts.value = PostResource.loading(null)
        val authUserId = sessionManager.getAuthUser()?.value?.data?.id
        if(authUserId!=null){
            val source = mainApi.getPostsFromUser(authUserId).
                observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .onErrorReturn {
                    mutableListOf()
                }

            source.subscribeWith(object:DisposableSingleObserver<List<Post>>(){
                override fun onSuccess(t: List<Post>) {
                    if(t.isNotEmpty()){
                        posts.value = PostResource.success(t)
                    } else {
                        posts.value = PostResource.error("list is empty",null)
                    }
                }

                override fun onError(e: Throwable) {
                    posts.value = PostResource.error(e.localizedMessage!!,null)
                }
            })

        }else{
            posts.value = PostResource.error("Authenticated user could not be found",null)
        }
    }

    fun observerPosts():LiveData<PostResource<List<Post>>>{
        return posts
    }

}
package com.serhankhan.posts.ui.main

class PostResource<T>(var status:Status,var data:T?,var message:String?) {



    companion object {

        fun <T> success(data:T?):PostResource<T>{
            return PostResource(Status.SUCCESS,data,null)
        }

        fun <T> error(message:String,data:T?):PostResource<T>{
            return PostResource(Status.ERROR,data,message)
        }

        fun<T> loading(data:T?):PostResource<T>{
            return PostResource(Status.LOADING,data,null)
        }
    }




    enum class Status { SUCCESS,ERROR,LOADING}
}
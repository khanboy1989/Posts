package com.serhankhan.posts.ui.auth

class AuthResource<T>(var status:AuthStatus?,var data:T?,var message:String?) {


    companion object {
        fun <T> authenticated(data:T):AuthResource<T>{
            return AuthResource(AuthStatus.AUTHENTICATED,data,null)
        }

        fun <T> error(message: String?,data:T?):AuthResource<T>{
            return AuthResource(AuthStatus.ERROR,data,message)
        }

        fun <T> loading(data:T?):AuthResource<T>{
            return AuthResource(AuthStatus.LOADING,data,null)
        }

        fun <T> logout():AuthResource<T>{
            return AuthResource(AuthStatus.NOT_AUTHENTICATED,null,null)
        }
    }


    enum class AuthStatus {
        AUTHENTICATED,ERROR,LOADING,NOT_AUTHENTICATED
    }

}


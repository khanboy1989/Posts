package com.serhankhan.posts.viewmodels.auth

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.serhankhan.posts.SessionManager
import com.serhankhan.posts.model.auth.User
import com.serhankhan.posts.remote_data_manager.auth.AuthApi
import com.serhankhan.posts.ui.auth.AuthResource
import javax.inject.Inject


class AuthViewModel @Inject constructor(private val authApi: AuthApi, private val sessionManager:SessionManager) :
    ViewModel() {

    private val TAG: String = AuthViewModel::class.java.simpleName

    @SuppressLint("CheckResult")
    fun autheticateWithId(userId: Int) {
        Log.d(TAG,"autheticateWithId:" + "attempting to login...")
        sessionManager.authenticateWithId(userId,authApi)
    }



    fun observeAuthState(): LiveData<AuthResource<User>>? {
        return sessionManager.getAuthUser()
    }
}
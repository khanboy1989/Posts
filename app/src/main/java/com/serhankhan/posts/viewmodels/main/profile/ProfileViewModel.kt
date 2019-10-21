package com.serhankhan.posts.viewmodels.main.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.serhankhan.posts.SessionManager
import com.serhankhan.posts.model.auth.User
import com.serhankhan.posts.ui.auth.AuthResource
import javax.inject.Inject

class ProfileViewModel @Inject constructor(private val sessionManager: SessionManager):ViewModel() {

    private var TAG:String = ProfileViewModel::class.java.simpleName


    fun getAuthenticatedUser():LiveData<AuthResource<User>>?{
        return sessionManager.getAuthUser()
    }

}
package com.serhankhan.posts

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.serhankhan.posts.model.auth.User
import com.serhankhan.posts.remote_data_manager.auth.AuthApi
import com.serhankhan.posts.ui.auth.AuthResource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor() {

    private var TAG = SessionManager::class.java.simpleName

    private var cachedUser: MutableLiveData<AuthResource<User>>? = MutableLiveData<AuthResource<User>>()

    @SuppressLint("CheckResult")
    fun authenticateWithId(userId:Int, authApi:AuthApi) {
        if(cachedUser!=null){
            cachedUser?.value = AuthResource.loading(null)
            val source = authApi.getUser(userId).observeOn(AndroidSchedulers.mainThread())
               .subscribeOn(Schedulers.io()).onErrorReturn {
                   User(-1, null, null, null)
               }

           source.subscribeWith(object : DisposableSingleObserver<User>() {
               override fun onSuccess(t: User) {
                   if (t.id == -1) {
                       cachedUser?.value = AuthResource.error("could not authenticated", null)
                   } else {
                       cachedUser?.value = AuthResource.authenticated(t)
                   }
               }

               override fun onError(e: Throwable) {
                   cachedUser?.value = AuthResource.error(e.localizedMessage, null)
               }
           })

       }
    }

    fun logOut(){
        Log.d(TAG,"logOut: logging out.....")
        cachedUser?.value = AuthResource.logout()
    }

    fun getAuthUser():LiveData<AuthResource<User>>?{
        return cachedUser
    }
}
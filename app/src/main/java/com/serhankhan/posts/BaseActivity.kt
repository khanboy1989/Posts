package com.serhankhan.posts

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.serhankhan.posts.model.auth.User
import com.serhankhan.posts.ui.auth.AuthActivity
import com.serhankhan.posts.ui.auth.AuthResource
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity: DaggerAppCompatActivity() {


    private var TAG:String = BaseActivity::class.java.simpleName

    @Inject
    lateinit var sessionManager:SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeObservers()
    }


    private fun subscribeObservers(){
        sessionManager.getAuthUser()?.observe(this, object:androidx.lifecycle.Observer<AuthResource<User>>{
            override fun onChanged(t: AuthResource<User>?) {
                if(t!=null){
                    when(t.status){
                        AuthResource.AuthStatus.LOADING->{
                        }

                        AuthResource.AuthStatus.AUTHENTICATED->{
                            Log.d(TAG,"onChanged:" + "LOGIN SUCCESS: " + t.data?.email)
                        }

                        AuthResource.AuthStatus.ERROR->{
                            Toast.makeText(this@BaseActivity,t.message + "\n Did you enter a number between 1 and 10?" , Toast.LENGTH_LONG)
                                .show()
                        }

                        AuthResource.AuthStatus.NOT_AUTHENTICATED->{
                            navLoginScreen()
                        }
                    }
                }
            }

        })
    }

    private fun navLoginScreen(){
        val intent: Intent = Intent(this,AuthActivity::class.java)
        startActivity(intent)
        finish()
    }
}
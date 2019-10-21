package com.serhankhan.posts.ui.auth

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.RequestManager
import com.serhankhan.posts.R
import com.serhankhan.posts.model.auth.User
import com.serhankhan.posts.ui.main.MainActivity
import com.serhankhan.posts.viewmodels.ViewModelProviderFactory
import com.serhankhan.posts.viewmodels.auth.AuthViewModel
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_auth.*
import javax.inject.Inject

class AuthActivity : DaggerAppCompatActivity(), View.OnClickListener {

    private val TAG = AuthActivity::class.java.simpleName

    @Inject
    lateinit var providerFactory:ViewModelProviderFactory

    @Inject
    lateinit var logo:Drawable

    private var viewModel: AuthViewModel? = null

    @Inject lateinit var
    requestManager:RequestManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        setLogo()

        viewModel = ViewModelProviders.of(this,providerFactory).get(AuthViewModel::class.java)

        initViewData(savedInstanceState)
        subscribeObserver()


    }

    private fun initViewData(savedInstanceState: Bundle?){
        login_button.setOnClickListener(this)
    }

    private fun subscribeObserver(){
        viewModel?.observeAuthState()?.observe(this,object:Observer<AuthResource<User>>{
            override fun onChanged(t: AuthResource<User>?) {
                if(t!=null){
                    when(t.status){
                        AuthResource.AuthStatus.LOADING->{
                            showProgressBar(true)
                        }

                        AuthResource.AuthStatus.AUTHENTICATED->{
                            showProgressBar(false)
                            Log.d(TAG,"onChanged:" + "LOGIN SUCCESS: " + t.data?.email)
                            onLoginSuccess()
                        }

                        AuthResource.AuthStatus.ERROR->{
                            showProgressBar(false)
                            Toast.makeText(this@AuthActivity,t.message + "\n Did you enter a number between 1 and 10?" , Toast.LENGTH_LONG)
                                .show()
                        }

                        AuthResource.AuthStatus.NOT_AUTHENTICATED->{
                            showProgressBar(false)

                        }
                    }
                }
            }
        })
    }


    private fun onLoginSuccess(){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun showProgressBar(isVisible:Boolean){
        if(isVisible){
            progress_bar.visibility = View.VISIBLE
        }else {
            progress_bar.visibility = View.GONE
        }
    }

    private fun setLogo(){
        requestManager.load(logo).into(login_logo)
    }

    override fun onClick(v: View?) {
        when(v){
            login_button->attemptLogin()
        }
    }

    private fun attemptLogin() {
        if(TextUtils.isEmpty(user_id_input.text.toString())){
            return
        }
        viewModel?.autheticateWithId(Integer.parseInt(user_id_input.text.toString()))
    }

}

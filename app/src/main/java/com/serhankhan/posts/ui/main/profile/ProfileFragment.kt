package com.serhankhan.posts.ui.main.profile


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.serhankhan.posts.R
import com.serhankhan.posts.model.auth.User
import com.serhankhan.posts.ui.auth.AuthResource
import com.serhankhan.posts.viewmodels.ViewModelProviderFactory
import com.serhankhan.posts.viewmodels.main.profile.ProfileViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_profile.*
import javax.inject.Inject


class ProfileFragment : DaggerFragment() {

    private var TAG:String = ProfileFragment::class.java.simpleName
    private var  viewModel: ProfileViewModel? = null

    @Inject
    lateinit var provideFactory:ViewModelProviderFactory


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG,"ProfileFragment was created...")
        viewModel = ViewModelProviders.of(this,provideFactory).get(ProfileViewModel::class.java)
        subscribeObservers()
    }


    private fun subscribeObservers(){
        viewModel?.getAuthenticatedUser()?.removeObservers(viewLifecycleOwner)
        viewModel?.getAuthenticatedUser()?.observe(viewLifecycleOwner,object:Observer<AuthResource<User>>{

            override fun onChanged(t: AuthResource<User>?) {
                if(t!=null){
                    when(t.status){
                        AuthResource.AuthStatus.AUTHENTICATED->{
                            setUserDetails(t.data)
                        }

                        AuthResource.AuthStatus.ERROR->{
                           setErrorDetails(t.message)
                        }
                    }
                }
            }

        })
    }

    private fun setErrorDetails(message: String?) {
        email.text = message
        username.text = "Error"
        website.text = "Error"
    }

    private fun setUserDetails(data: User?) {

        email.text = data?.email
        username.text = data?.username
        website.text = data?.website

    }




}

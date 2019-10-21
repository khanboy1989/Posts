package com.serhankhan.posts.ui.main.post


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders

import com.serhankhan.posts.R
import com.serhankhan.posts.model.post.Post
import com.serhankhan.posts.ui.main.PostResource
import com.serhankhan.posts.util.VerticalSpacingItemDecoration
import com.serhankhan.posts.viewmodels.ViewModelProviderFactory
import com.serhankhan.posts.viewmodels.main.post.PostViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_post.*
import javax.inject.Inject


class PostFragment : DaggerFragment() {

    private lateinit var viewModel: PostViewModel

    private var TAG = this::class.java.simpleName

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    @Inject
    lateinit var adapter:PostsRecyclerAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_post, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this, providerFactory).get(PostViewModel::class.java)
        viewModel.getPosts()

        observePosts()
        initRecyclerView()
    }

    private fun observePosts() {

        viewModel.observerPosts().observe(this, object : androidx.lifecycle.Observer<PostResource<List<Post>>> {

            override fun onChanged(t: PostResource<List<Post>>?) {
                if (t != null) {
                    when (t.status) {
                        PostResource.Status.LOADING->{
                            Log.d(TAG,"onChanged: Loading..." )

                        }

                        PostResource.Status.SUCCESS->{
                            Log.d(TAG,"onChanged: got posts...." + t.data?.size)
                            if(t.data!= null && t.data!!.size > 0 ){
                                adapter.updateData(t.data!!)
                            }
                        }

                        PostResource.Status.ERROR->{
                            Log.d(TAG,"onChanged: Error...." + t.message)

                        }
                    }
                }
            }
        })
    }


    private fun initRecyclerView(){
        recycler_view.addItemDecoration(VerticalSpacingItemDecoration(15))
        recycler_view.adapter = adapter
    }
}

package com.serhankhan.posts.ui.main.post

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.serhankhan.posts.R
import com.serhankhan.posts.model.post.Post
import kotlinx.android.synthetic.main.layout_post_list_item.view.*

class PostsRecyclerAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var postsList:MutableList<Post> = mutableListOf()

    fun updateData(posts:List<Post>){
        postsList.clear()
        postsList.addAll(posts)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PostsListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_post_list_item,parent,false))
    }

    override fun getItemCount(): Int = postsList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is PostsListViewHolder->holder.bind(postsList[position])
        }
    }

    inner class PostsListViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        private val title = itemView.title

        fun bind(item:Post){
            title.text = item.title
        }
    }
}
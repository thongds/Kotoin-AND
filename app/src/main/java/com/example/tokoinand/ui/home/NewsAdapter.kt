package com.example.tokoinand.ui.home

import android.content.Context
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.example.tokoinand.R
import com.example.tokoinand.commonModel.ArticleItem
import com.example.tokoinand.databinding.ItemHomeAdapterLayoutBinding
import com.example.tokoinand.ui.newsDetail.NEWS_CONTENT
import com.example.tokoinand.ui.newsDetail.NEWS_LINK
import com.example.tokoinand.ui.newsDetail.NEWS_LINK_THUMB
import com.example.tokoinand.ui.newsDetail.NEWS_TITLE

class NewsAdapter(val fragment : Fragment, private val actionId : Int) :ListAdapter<ArticleItem, NewsAdapter.ViewHolder>(ArticleDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemHomeAdapterLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(parent.context,fragment,actionId,binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindingData(getItem(position))
    }

    inner class ViewHolder(private val context: Context,private val fragment: Fragment,private val actionId: Int,private val binding: ItemHomeAdapterLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindingData(articleItem: ArticleItem){
            Glide.with(context).load(articleItem.urlToImage).placeholder(context.resources.getDrawable(
                R.drawable.default_thumb,null))
                .into(binding.thumb)
            binding.title.text = articleItem.title
            binding.descript.text = articleItem.description
            binding.cardHolder.setOnClickListener {
                val bundle = Bundle()
                bundle.putString(NEWS_LINK_THUMB,articleItem.urlToImage)
                bundle.putString(NEWS_LINK,articleItem.url)
                bundle.putString(NEWS_TITLE,articleItem.title)
                bundle.putString(NEWS_CONTENT,articleItem.content)
                fragment.findNavController().navigate(actionId,bundle)
            }
        }
        //prevent duplicate image when network's slow state
        fun cleanAsyncData(){
            binding.thumb.setImageBitmap(BitmapFactory.decodeResource(context.resources,R.drawable.default_thumb))
        }
    }
}
class ArticleDiffCallback : DiffUtil.ItemCallback<ArticleItem>(){
    override fun areContentsTheSame(oldItem: ArticleItem, newItem: ArticleItem): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: ArticleItem, newItem: ArticleItem): Boolean {
        return oldItem.url == newItem.url
    }
}
package com.example.tokoinand.ui.newsDetail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.tokoinand.R
import com.example.tokoinand.databinding.NewsDetailFragmentBinding

const val NEWS_CONTENT = "news_content"
const val NEWS_TITLE = "news_title"
const val NEWS_LINK = "news_link"
const val NEWS_LINK_THUMB = "news_link_thumb"
class NewsDetailFragment : Fragment() {

    private lateinit var viewModel: NewsDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = NewsDetailFragmentBinding.inflate(LayoutInflater.from(requireContext()),container,false)
        val newsContent = arguments?.get(NEWS_CONTENT) as? String
        val newsTitle = arguments?.get(NEWS_TITLE) as? String
        val newsLink = arguments?.get(NEWS_LINK) as? String
        val newLinkThumb = arguments?.get(NEWS_LINK_THUMB) as? String
        newsContent?.let {
            binding.content.text = it
        }
        newsTitle?.let {
            binding.title.text = it
        }
        newsLink?.let {link ->
            binding.url.text = link
            binding.url.setOnClickListener {
                findNavController().navigate(NewsDetailFragmentDirections.actionNewsDetailFragment3ToWebviewFragment(link))
            }
        }
        newLinkThumb?.let {
            Glide.with(requireContext()).load(it)
                .placeholder(requireContext().resources.getDrawable(R.drawable.default_thumb,null)).into(binding.thumb)

        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NewsDetailViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
        val activity = requireActivity() as AppCompatActivity
        activity.supportActionBar?.show()
        activity.supportActionBar?.title = "News Detail"
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
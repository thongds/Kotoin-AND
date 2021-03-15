package com.example.tokoinand.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tokoinand.databinding.HomeFragmentLayoutBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    lateinit var viewModel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        val binding = HomeFragmentLayoutBinding.inflate(LayoutInflater.from(requireContext()),container,false)
        binding.rycHome.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.requestTopNews()
        }
        val adapter = NewsAdapter(this,HomeFragmentDirections.actionHomeFragmentToNewsDetailFragment().actionId)
        binding.rycHome.adapter = adapter
        binding.rycHome.setRecyclerListener { holder ->
            val newHolder = holder as NewsAdapter.ViewHolder
            newHolder.cleanAsyncData()
        }
        viewModel.newListData.observe(viewLifecycleOwner, Observer {
            binding.swipeRefresh.isRefreshing = false
            adapter.submitList(it.articles)
        })
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val active = requireActivity() as AppCompatActivity
        active.supportActionBar?.hide()
    }
}
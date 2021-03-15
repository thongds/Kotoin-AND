package com.example.tokoinand.ui.customNews

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tokoinand.R
import com.example.tokoinand.databinding.CustomNewsFragmentBinding
import com.example.tokoinand.ui.home.NewsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CustomNewsFragment : Fragment() {

    lateinit var viewModel: CustomNewsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProvider(this).get(CustomNewsViewModel::class.java)
        val binding = CustomNewsFragmentBinding.inflate(LayoutInflater.from(requireContext()), container, false)
        binding.rycHome.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.requestTopNews()
        }
        val adapter = NewsAdapter(this,CustomNewsFragmentDirections.actionCustomNewsFragmentToNewsDetailFragment2().actionId)
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
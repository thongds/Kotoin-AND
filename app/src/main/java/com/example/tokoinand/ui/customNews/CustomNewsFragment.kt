package com.example.tokoinand.ui.customNews

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tokoinand.R
import com.example.tokoinand.SharePreferenceUtil
import com.example.tokoinand.databinding.CustomNewsFragmentBinding
import com.example.tokoinand.ui.home.NewsAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CustomNewsFragment : Fragment() {

    lateinit var viewModel: CustomNewsViewModel
    lateinit var binding: CustomNewsFragmentBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProvider(this).get(CustomNewsViewModel::class.java)
        binding = CustomNewsFragmentBinding.inflate(LayoutInflater.from(requireContext()), container, false)
        binding.rycHome.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.requestNewsByCategory(SharePreferenceUtil(requireActivity()).getCategory())
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
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.news_array,
            android.R.layout.simple_spinner_item
        ).also { newsAdapter ->
            newsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinner.adapter = newsAdapter
        }
        binding.spinner.onItemSelectedListener = object : OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                parent?.let {
                    val selectedName = it.adapter.getItem(position) as? String
                    selectedName?.let {category ->
                        viewModel.requestNewsByCategory(category)
                        SharePreferenceUtil(requireActivity()).saveCategory(category)
                    }
                }

            }

        }
        return binding.root
    }
    override fun onResume() {
        super.onResume()
        val active = requireActivity() as AppCompatActivity
        active.supportActionBar?.hide()
    }
}
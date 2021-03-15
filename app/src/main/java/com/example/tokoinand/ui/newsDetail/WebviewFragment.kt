package com.example.tokoinand.ui.newsDetail

import android.app.ProgressDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.example.tokoinand.R
import com.example.tokoinand.databinding.WebviewFragmentBinding

class WebviewFragment : Fragment() {


    private lateinit var viewModel: WebviewViewModel
    lateinit var binding : WebviewFragmentBinding
    val args : WebviewFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = WebviewFragmentBinding.inflate(LayoutInflater.from(requireContext()),container,false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(WebviewViewModel::class.java)
        binding.webview.settings.javaScriptEnabled = true
        val progress = ProgressDialog.show(requireContext(), "", "Loading...",true)
        binding.webview.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                if(progress!=null && progress.isShowing)
                {
                    progress.dismiss()
                }
            }
        }
        binding.webview.loadUrl(args.link)
    }

}
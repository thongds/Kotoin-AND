package com.example.tokoinand.ui.register

import android.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.tokoinand.R
import com.example.tokoinand.databinding.LoginFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var viewModel: LoginViewModel
    lateinit var binding: LoginFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LoginFragmentBinding.inflate(LayoutInflater.from(requireContext()),container,false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.createAccount.setOnClickListener {
            it.findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
        }
        viewModel.message.observe(viewLifecycleOwner, Observer {
            it?.let {
                val alertDialog = AlertDialog.Builder(requireActivity())
                alertDialog.setTitle("Alert")
                alertDialog.setMessage(it)
                alertDialog.setPositiveButton("ok") { dialog, _ ->
                    dialog.dismiss()
                }
                alertDialog.show()
            }
        })
        viewModel.loginStatus.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it){
                    findNavController().popBackStack()
                }
            }
        })
        binding.loginBtn.setOnClickListener {
            viewModel.login(requireActivity(),binding.passwordEdit.text.toString(),binding.userNameEditText.text.toString())
        }
    }

    override fun onResume() {
        super.onResume()
        val active = requireActivity() as AppCompatActivity
        active.supportActionBar?.hide()
    }
}
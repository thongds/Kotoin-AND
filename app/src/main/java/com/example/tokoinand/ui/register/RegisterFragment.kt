package com.example.tokoinand.ui.register

import android.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.tokoinand.R
import com.example.tokoinand.SharePreferenceUtil
import com.example.tokoinand.databinding.RegisterFragmentBinding
import com.example.tokoinand.ui.profile.UserProfile
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private lateinit var viewModel: RegisterViewModel
    lateinit var binding : RegisterFragmentBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = RegisterFragmentBinding.inflate(LayoutInflater.from(requireContext()),container,false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
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
        viewModel.createStatus.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it){
                    findNavController().popBackStack()
                }
            }
        })
        binding.registerBtn.setOnClickListener {
            viewModel.createUser(requireActivity(),binding.passwordEdit.text.toString(),binding.confirmPassword.text.toString(),binding.userNameEditText.text.toString())
        }
    }

    override fun onResume() {
        super.onResume()
        val active = requireActivity() as AppCompatActivity
        active.supportActionBar?.show()
        active.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

}
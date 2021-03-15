package com.example.tokoinand.ui.register

import android.app.Activity
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tokoinand.DefaultRepository
import com.example.tokoinand.SharePreferenceUtil
import com.example.tokoinand.ui.profile.UserProfile
import kotlinx.coroutines.launch

class RegisterViewModel @ViewModelInject constructor(private val defaultRepository: DefaultRepository) : ViewModel() {

    var message = MutableLiveData<String?>()
    var createStatus = MutableLiveData<Boolean?>()
    fun createUser(activity : Activity,password: String,confirmPassword : String,userName: String){
        if (password.isBlank()){
            message.value = "password can't blank"
            return
        }
        if (password.trim() != confirmPassword.trim()){
            message.value = "password doesn't match"
            return
        }
        if (userName.trim().isBlank()){
            message.value = "User Name can't blank"
            return
        }
        viewModelScope.launch {
            val userEntry = defaultRepository.getUserByName(userName)
            if (userEntry != null){
                message.value = "User Name already exists"
            }else{
                defaultRepository.insertUser(userName,password)
                SharePreferenceUtil(activity).saveProfile(UserProfile(userName))
                createStatus.value = true
            }
        }
    }
}
package com.example.tokoinand.ui.register

import android.content.Context
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tokoinand.DefaultRepository
import com.example.tokoinand.SharePreferenceUtil
import com.example.tokoinand.ui.profile.UserProfile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel @ViewModelInject constructor(val defaultRepository: DefaultRepository) : ViewModel() {
    var message = MutableLiveData<String?>()
    var loginStatus = MutableLiveData<Boolean?>()
    fun login(context : Context, password: String, userName: String){
        viewModelScope.launch(Dispatchers.IO) {
            val userEntry = defaultRepository.getUser(userName,password)
            if (userEntry == null){
                message.value = "User and password didn't found"
                loginStatus.value = false
            }else{
                SharePreferenceUtil(context).saveProfile(UserProfile(userName))
                loginStatus.value = true
            }
        }
    }
}
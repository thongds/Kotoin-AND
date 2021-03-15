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

class LoginViewModel @ViewModelInject constructor(val defaultRepository: DefaultRepository) : ViewModel() {
    var message = MutableLiveData<String?>()
    var loginStatus = MutableLiveData<Boolean?>()
    fun login(activity : Activity, password: String, userName: String){
        viewModelScope.launch {
            val userEntry = defaultRepository.getUser(userName,password)
            if (userEntry == null){
                message.value = "User and password didn't found"
            }else{
                SharePreferenceUtil(activity).saveProfile(UserProfile(userName))
                loginStatus.value = true
            }
        }
    }
}
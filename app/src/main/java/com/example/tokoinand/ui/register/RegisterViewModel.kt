package com.example.tokoinand.ui.register

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tokoinand.DefaultRepository
import kotlinx.coroutines.launch

class RegisterViewModel @ViewModelInject constructor(private val defaultRepository: DefaultRepository) : ViewModel() {

    var message = MutableLiveData<String?>()
    var createStatus = MutableLiveData<Boolean?>()
    fun createUser(password: String,confirmPassword : String,userName: String){
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
                createStatus.value = true
            }
        }
    }
}
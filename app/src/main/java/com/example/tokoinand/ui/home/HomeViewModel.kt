package com.example.tokoinand.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tokoinand.DefaultRepository
import com.example.tokoinand.commonModel.NewsListModel
import kotlinx.coroutines.launch
import java.lang.Exception

class HomeViewModel @ViewModelInject constructor(private val defaultRepository: DefaultRepository) : ViewModel() {
    private val _newListData = MutableLiveData<NewsListModel>()
    val networkError = MutableLiveData<String?>()
    val newListData : LiveData<NewsListModel>
        get() = _newListData
    init {
        requestTopNews()
    }
    fun requestTopNews(){
        viewModelScope.launch {
            try {
                _newListData.value =  defaultRepository.requestTopHeadlineNews("us")
            }catch (err : Exception){
                networkError.value = "Network error!"
            }
        }
    }
}
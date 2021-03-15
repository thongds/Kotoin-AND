package com.example.tokoinand.ui.customNews

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tokoinand.DefaultRepository
import com.example.tokoinand.commonModel.NewsListModel
import kotlinx.coroutines.launch

class CustomNewsViewModel  @ViewModelInject constructor(private val defaultRepository: DefaultRepository) : ViewModel() {
    private val _newListData = MutableLiveData<NewsListModel>()
    val newListData : LiveData<NewsListModel>
        get() = _newListData
    init {
        requestTopNews()
    }
    fun requestTopNews(){
        viewModelScope.launch {
            _newListData.value =  defaultRepository.requestTopHeadlineNews("us")
        }
    }
}
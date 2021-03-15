package com.example.tokoinand.ui.customNews

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tokoinand.DefaultRepository
import com.example.tokoinand.commonModel.NewsListModel
import kotlinx.coroutines.launch
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class CustomNewsViewModel  @ViewModelInject constructor(private val defaultRepository: DefaultRepository) : ViewModel() {
    private val _newListData = MutableLiveData<NewsListModel>()
    val networkError = MutableLiveData<String?>()
    val newListData : LiveData<NewsListModel>
        get() = _newListData
    fun requestNewsByCategory(category : String){

        viewModelScope.launch {
            val today = SimpleDateFormat("yyyy-MM-dd", Locale.ROOT).format(Date())
            try {
                _newListData.value =  defaultRepository.getNewsByCategory(category,today)
            }catch (err: Exception){
                networkError.value = "Network error"
            }
        }
    }
}
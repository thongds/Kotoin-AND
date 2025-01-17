package com.example.tokoinand

import com.example.tokoinand.commonModel.NewsListModel
import com.example.tokoinand.network.NetworkApiList
import com.example.tokoinand.room.TOKOINDao
import com.example.tokoinand.room.UserEntry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

const val API_KEY = "7392f4421fb24a7dbfe8e09a79c272e4"
class DefaultRepository @Inject constructor(private val networkApiList: NetworkApiList, private val dao : TOKOINDao) {
    suspend fun requestTopHeadlineNews(countryCode : String) : NewsListModel {
        return networkApiList.getTopHeadlineNewsAsync(countryCode, API_KEY).await()
    }
    suspend fun getUser(userName : String,password : String) : UserEntry?{
        return withContext(Dispatchers.IO){
             dao.getUser(userName,password)
        }
    }
    suspend fun getUserByName(userName : String) : UserEntry?{
        return withContext(Dispatchers.IO){
             dao.getUserByName(userName)
        }
    }
    suspend fun insertUser(userName: String,password: String){
        withContext(Dispatchers.IO){
            dao.insertUser(UserEntry(userName = userName,password = password))
        }
    }
    suspend fun getNewsByCategory(category: String,from : String) : NewsListModel{
        return networkApiList.getNewsByCategoryAsync(category,from, API_KEY).await()
    }
}
package com.example.tokoinand

import com.example.tokoinand.commonModel.NewsListModel
import com.example.tokoinand.network.NetworkApiList
import com.example.tokoinand.room.TOKOINDao
import com.example.tokoinand.room.UserEntry
import javax.inject.Inject

const val API_KEY = "7392f4421fb24a7dbfe8e09a79c272e4"
class DefaultRepository @Inject constructor(private val networkApiList: NetworkApiList, private val dao : TOKOINDao) {
    suspend fun requestTopHeadlineNews(countryCode : String) : NewsListModel {
        return networkApiList.getTopHeadlineNewsAsync(countryCode, API_KEY).await()
    }
    suspend fun getUser(userName : String,password : String) : UserEntry?{
        return dao.getUser(userName,password)
    }
    suspend fun getUserByName(userName : String) : UserEntry?{
        return dao.getUserByEmail(userName)
    }
    suspend fun insertUser(userName: String,password: String){
        return dao.insertUser(UserEntry(userName = userName,password = password))
    }
}
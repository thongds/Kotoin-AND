package com.example.tokoinand.room

import androidx.room.*

@Dao
interface TOKOINDao {
    @Insert
    suspend fun insertUser(userEntry: UserEntry)

    @Query("SELECT * from user where user_name = :userName AND password = :password")
    suspend fun getUser(userName : String,password : String) : UserEntry?

    @Query("SELECT * from user where user_name = :userName")
    suspend fun getUserByName(userName : String) : UserEntry?
}
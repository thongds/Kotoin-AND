package com.example.tokoinand.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntry(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id : Long = 0,
    @ColumnInfo(name = "created")
    var created : Long = 0,
    @ColumnInfo(name = "user_name")
    var userName : String,
    @ColumnInfo(name = "password")
    var password : String
)
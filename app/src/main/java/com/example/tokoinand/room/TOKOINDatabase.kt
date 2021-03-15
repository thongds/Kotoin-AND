package com.example.tokoinand.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserEntry::class], version = 1, exportSchema = false)
abstract class TOKOINDatabase : RoomDatabase() {

    abstract val databaseDao: TOKOINDao
    companion object {
        @Volatile
        private var INSTANCE: TOKOINDatabase? = null
        fun getInstance(context: Context): TOKOINDatabase {
            //prevent thread race condition
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TOKOINDatabase::class.java,
                        "TOKOIN.db"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
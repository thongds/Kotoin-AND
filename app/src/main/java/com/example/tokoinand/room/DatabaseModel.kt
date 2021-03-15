package com.example.tokoinand.room

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class DatabaseModel {
    @Provides
    fun provideDao(database: TOKOINDatabase) : TOKOINDao{
        return  database.databaseDao
    }
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext : Context) : TOKOINDatabase{
        return  Room.databaseBuilder(appContext,TOKOINDatabase::class.java,"TOKOIN.db").build()
    }
}
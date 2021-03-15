package com.example.tokoinand.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.tokoinand.room.TOKOINDatabase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.*
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class UserEntryTest{
    private lateinit var database: TOKOINDatabase
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    @Before
    fun initDb() {
        // database is stored in memory
        database = Room.inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
                TOKOINDatabase::class.java
        ).build()
    }
    @Test
    fun insertLatLn() = runBlockingTest {

    }

    @After
    fun closeDb() = database.close()
}
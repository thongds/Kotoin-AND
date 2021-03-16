package com.example.tokoinand.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.tokoinand.room.TOKOINDatabase
import com.example.tokoinand.room.UserEntry
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers
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
    fun insertUser() = runBlockingTest {
        val userName = "thongds"
        val password = "123456"
        val user = UserEntry(userName = userName,password = password)
        database.databaseDao.insertUser(user)
        val userFromDb = database.databaseDao.getUserByName(userName)
        Assert.assertThat(userFromDb!!.userName, CoreMatchers.`is`(userName))
    }
    @Test
    fun insertUserEmpty() = runBlockingTest {
        val userName = ""
        val password = "123456"
        val user = UserEntry(userName = userName,password = password)
        database.databaseDao.insertUser(user)
        val userFromDb = database.databaseDao.getUserByName(userName)
        Assert.assertThat(userFromDb!!.userName, CoreMatchers.`is`(userName))
    }
    @Test
    fun insertUserPasswordEmpty() = runBlockingTest {
        val userName = "thongds"
        val password = ""
        val user = UserEntry(userName = userName,password = password)
        database.databaseDao.insertUser(user)
        val userFromDb = database.databaseDao.getUserByName(userName)
        Assert.assertThat(userFromDb!!.password, CoreMatchers.`is`(password))
    }
    @After
    fun closeDb() = database.close()
}
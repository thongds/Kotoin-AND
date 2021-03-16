package com.example.tokoinand.ui.register

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.tokoinand.DefaultRepository
import com.example.tokoinand.getOrAwaitValue
import com.example.tokoinand.network.ApplicationApi
import com.example.tokoinand.room.DatabaseModel
import com.example.tokoinand.room.TOKOINDatabase
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RegisterViewModelTest{
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    private lateinit var registerViewModel : RegisterViewModel
    @Before
    fun setupViewModel() {
        val defaultRepository = DefaultRepository(ApplicationApi.mainService(),DatabaseModel().provideDao(TOKOINDatabase.getInstance(ApplicationProvider.getApplicationContext())))
        registerViewModel = RegisterViewModel(defaultRepository)
    }
    @Test
    fun createNewUser_add_blank_user(){
        registerViewModel.createUser(ApplicationProvider.getApplicationContext(),"1","1","")
        val message = registerViewModel.msg.getOrAwaitValue()
        Assert.assertEquals(message,"User Name can't blank")
    }
    @Test
    fun createNewUser_add_blank_password(){
        registerViewModel.createUser(ApplicationProvider.getApplicationContext(),"","1","thongds")
        val message = registerViewModel.msg.getOrAwaitValue()
        Assert.assertEquals(message,"password can't blank")
    }
    @Test
    fun createNewUser_add_not_match_password(){
        registerViewModel.createUser(ApplicationProvider.getApplicationContext(),"1","2","thongds")
        val message = registerViewModel.msg.getOrAwaitValue()
        Assert.assertEquals(message,"password doesn't match")
    }
}
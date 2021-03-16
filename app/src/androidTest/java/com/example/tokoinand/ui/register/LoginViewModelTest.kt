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
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    private lateinit var registerViewModel : RegisterViewModel
    private lateinit var loginViewModel: LoginViewModel
    @Before
    fun setupViewModel() {
        val service =  ApplicationApi.mainService()
        val database = DatabaseModel().provideDao(TOKOINDatabase.getInstance(ApplicationProvider.getApplicationContext()))
        val defaultRepository = DefaultRepository(
            service,database)
        registerViewModel = RegisterViewModel(defaultRepository)
        loginViewModel = LoginViewModel(defaultRepository)
    }
    @Test
    fun login_input_correct() {
        registerViewModel.createUser(ApplicationProvider.getApplicationContext(),"1","1","thongds")
        loginViewModel.login(ApplicationProvider.getApplicationContext(),"1","thongds")
        val status = loginViewModel.loginStatus.getOrAwaitValue()
        assertEquals(status,true)
    }
    @Test
    fun login_input_wrong() {
        registerViewModel.createUser(ApplicationProvider.getApplicationContext(),"1","1","thongds")
        loginViewModel.login(ApplicationProvider.getApplicationContext(),"2","thongds")
        val status = loginViewModel.loginStatus.getOrAwaitValue()
        assertEquals(status,false)
    }
    @Test
    fun login_input_empty_password() {
        registerViewModel.createUser(ApplicationProvider.getApplicationContext(),"1","1","thongds")
        loginViewModel.login(ApplicationProvider.getApplicationContext(),"","thongds")
        val status = loginViewModel.loginStatus.getOrAwaitValue()
        assertEquals(status,false)
    }
    @Test
    fun login_input_empty_user() {
        registerViewModel.createUser(ApplicationProvider.getApplicationContext(),"1","1","thongds")
        loginViewModel.login(ApplicationProvider.getApplicationContext(),"1","")
        val status = loginViewModel.loginStatus.getOrAwaitValue()
        assertEquals(status,false)
    }
}
package com.example.tokoinand

import android.app.Application
import com.facebook.stetho.Stetho
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ApplicationApp : Application() {
    override fun onCreate() {
        super.onCreate()
        //monitor network
        Stetho.initializeWithDefaults(this)
    }
}
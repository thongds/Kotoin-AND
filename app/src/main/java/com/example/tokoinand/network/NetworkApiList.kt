package com.example.tokoinand.network

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

const val BASE_URL = "https://newsapi.org"
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
val httpClient: OkHttpClient = OkHttpClient.Builder().addNetworkInterceptor(StethoInterceptor()).build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .client(httpClient)
    .baseUrl(BASE_URL)
    .build()
@Module
@InstallIn(ApplicationComponent::class)
object ApplicationApi{
    @Provides
    fun mainService() : NetworkApiList {
        return  retrofit.create(NetworkApiList::class.java)
    }
}

interface NetworkApiList {

}
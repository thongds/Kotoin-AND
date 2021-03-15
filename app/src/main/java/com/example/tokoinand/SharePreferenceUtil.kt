package com.example.tokoinand


import android.app.Activity
import android.content.Context
import com.example.tokoinand.ui.profile.UserProfile
import com.squareup.moshi.Moshi

const val USER_PROFILE = "USER_PROFILE"
const val SHARE_KEY  = "SHARE_KEY"
class SharePreferenceUtil constructor(private val activity: Activity) {
    fun saveProfile(userProfile: UserProfile?){
        val moshi = Moshi.Builder().build()
        val jsonAdapter = moshi.adapter(UserProfile::class.java)
        val profileJson = jsonAdapter.toJson(userProfile)
        val sharedPref = activity.applicationContext.getSharedPreferences(SHARE_KEY,Context.MODE_PRIVATE)
        with (sharedPref.edit()) {
            putString(USER_PROFILE,profileJson)
            commit()
        }
    }
    fun getProfile() : UserProfile?{
        val sharedPref = activity.applicationContext.getSharedPreferences(SHARE_KEY,Context.MODE_PRIVATE)
        val profileString = sharedPref.getString(USER_PROFILE,"")
        if (profileString.isNullOrBlank()) return null
        val moshi = Moshi.Builder().build()
        val jsonAdapter = moshi.adapter(UserProfile::class.java)
        return jsonAdapter.fromJson(profileString)
    }

}
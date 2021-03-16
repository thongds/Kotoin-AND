package com.example.tokoinand


import android.content.Context
import com.example.tokoinand.ui.profile.UserProfile
import com.squareup.moshi.Moshi

const val USER_PROFILE = "USER_PROFILE"
const val SHARE_KEY  = "SHARE_KEY"
const val CURRENT_CATEGORY  = "CURRENT_CATEGORY"
class SharePreferenceUtil constructor(private val context: Context) {
    fun saveProfile(userProfile: UserProfile?){
        val moshi = Moshi.Builder().build()
        val jsonAdapter = moshi.adapter(UserProfile::class.java)
        val profileJson = jsonAdapter.toJson(userProfile)
        val sharedPref = context.getSharedPreferences(SHARE_KEY,Context.MODE_PRIVATE)
        with (sharedPref.edit()) {
            putString(USER_PROFILE,profileJson)
            commit()
        }
    }
    fun getProfile() : UserProfile?{
        val sharedPref = context.getSharedPreferences(SHARE_KEY,Context.MODE_PRIVATE)
        val profileString = sharedPref.getString(USER_PROFILE,"")
        if (profileString.isNullOrBlank()) return null
        val moshi = Moshi.Builder().build()
        val jsonAdapter = moshi.adapter(UserProfile::class.java)
        return jsonAdapter.fromJson(profileString)
    }
    fun getCategory() : String {
        val sharedPref = context.getSharedPreferences(SHARE_KEY,Context.MODE_PRIVATE)
        val langCodeString = sharedPref.getString(CURRENT_CATEGORY,"")
        if (langCodeString.isNullOrBlank()) return ""
        return langCodeString
    }
    fun saveCategory(langCode : String) {
        context.applicationContext.let {
            val share = it.getSharedPreferences(SHARE_KEY,Context.MODE_PRIVATE)
            with(share.edit()){
                putString(CURRENT_CATEGORY,langCode)
                commit()
            }
        }
    }
}
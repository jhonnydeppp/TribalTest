package com.jhonnydev.tribaltest.utils

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jhonnydev.tribaltest.models.PhotoResponse

object PreferencesUtils {

    private val TAG = PreferencesUtils::class.java.simpleName


    private var mSharedPref: SharedPreferences? = null

    fun init(context: Context) {
        if (mSharedPref == null) {
            mSharedPref = context.getSharedPreferences(TAG, Activity.MODE_PRIVATE)
        }
    }

    fun clearPreference() {
        mSharedPref!!.edit().clear().apply()
    }

    fun clearSinglePreference(key:String){
        mSharedPref!!.edit().remove(key).apply()
    }

    fun setFavoritesList(appList: List<PhotoResponse>) {
        val gson = Gson()
        val json = gson.toJson(appList)
        val editor = mSharedPref!!.edit()
        editor.putString(KeyPreferences.PREF_KEY_FAVORITE_LIST.value, json.toString())
        editor.apply()

    }

    fun getFavoritesList(): List<PhotoResponse> {
        val gson = Gson()
        val jsonPreferences = mSharedPref!!.getString(KeyPreferences.PREF_KEY_FAVORITE_LIST.value, "")
        return if(jsonPreferences.isNullOrEmpty())
            emptyList()
        else
            gson.fromJson(jsonPreferences, object : TypeToken<List<PhotoResponse>>() {}.type)
    }
}
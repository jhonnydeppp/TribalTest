package com.jhonnydev.tribaltest.utils

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

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
}
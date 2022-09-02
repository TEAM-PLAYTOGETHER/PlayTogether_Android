package com.playtogether_android.app.util

import android.content.Context
import android.util.Log

object PlayTogetherSharedPreference {
    private const val JWT_TOKEN = "JWT_TOKEN"

    fun getJwtToken(context: Context): String {
        val preferences = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
        return preferences.getString(JWT_TOKEN, "") ?: ""
        Log.d("testGet", preferences.getString(JWT_TOKEN, "") ?: "")
    }

    fun setJwtToken(context: Context, value: String) {
        val preferences = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
        preferences.edit().putString(JWT_TOKEN, value).apply()
        val data = preferences.all.values
        Log.d("testSet", preferences.getString(JWT_TOKEN, "") ?: "")
        Log.d("testSET", "" + data)
    }
}
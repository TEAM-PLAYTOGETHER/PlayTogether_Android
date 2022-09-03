package com.playtogether_android.app.util

import android.content.Context

object PlayTogetherSharedPreference {
    /*
    private const val JWT_TOKEN = "JWT_TOKEN"

    fun getJwtToken(context: Context): String {
        val preferences = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
        val token =
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjQ0IiwidXNlckxvZ2luSWQiOiJicmlhbmRyIiwiaWF0IjoxNjU5MjM2Mjg3LCJleHAiOjE2NjE4MjgyODcsImlzcyI6InBsYXl0b2dldGhlciJ9.N_qdonODmdOPpTdre-zY1RmZXnTFHeN72OTc3zYUTcY"
        return token
//        return preferences.getString(JWT_TOKEN, "") ?: ""
        Log.d("testGet", preferences.getString(JWT_TOKEN, "") ?: "")
    }

    fun setJwtToken(context: Context, value: String) {
        val preferences = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
        preferences.edit().putString(JWT_TOKEN, value).apply()
        val data = preferences.all.values
        Log.d("testSet", preferences.getString(JWT_TOKEN, "") ?: "")
        Log.d("testSET", "" + data)
    }

     */
    private const val ACCESS_TOKEN = "ACCESS_TOKEN"
    private const val REFRESH_TOKEN = "REFRESH_TOKEN"

    fun getAccessToken(context: Context): String {
        val preferences = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
        return preferences.getString(ACCESS_TOKEN, "") ?: ""
    }

    fun setAccessToken(context: Context, value: String) {
        val preferences = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
        preferences.edit().putString(ACCESS_TOKEN, value).apply()
    }

    fun removeAccessToken(context: Context) {
        val preferences = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
        preferences.edit().remove(ACCESS_TOKEN).apply()
    }

    fun getRefreshToken(context: Context): String {
        val preferences = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
        return preferences.getString(REFRESH_TOKEN, "") ?: ""
    }

    fun setRefreshToken(context: Context, value: String) {
        val preferences = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
        return preferences.edit().putString(REFRESH_TOKEN, value).apply()
    }

    fun removeRefreshToken(context: Context) {
        val preferences = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
        preferences.edit().remove(REFRESH_TOKEN).apply()
    }

}
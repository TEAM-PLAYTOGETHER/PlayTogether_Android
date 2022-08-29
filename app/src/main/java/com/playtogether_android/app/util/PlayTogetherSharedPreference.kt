package com.playtogether_android.app.util

import android.content.Context
import android.util.Log

object PlayTogetherSharedPreference {
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
}
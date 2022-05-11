package com.playtogether_android.app.util

import android.content.Context
import android.util.Log

object PlayTogetherSharedPreference {
    private const val JWT_TOKEN = "JWT_TOKEN"

    fun getJwtToken(context: Context): String {
        val preferences = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
        return preferences.getString(JWT_TOKEN, "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjEwIiwidXNlckxvZ2luSWQiOiJsZWVqZWp1bmUiLCJpYXQiOjE2NTE3MjkwOTMsImV4cCI6MTY1NDMyMTA5MywiaXNzIjoicGxheXRvZ2V0aGVyIn0.P01Vb3F6XkG2tJEhFa9lh2HCGxtib7lKMoHvJxP521U") ?: ""
    }

    fun setJwtToken(context: Context, value: String) {
        val preferences = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
        preferences.edit().putString(JWT_TOKEN, value).apply()
        val data = preferences.all.values
    }
}
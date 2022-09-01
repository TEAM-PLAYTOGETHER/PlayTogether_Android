package com.playtogether_android.data.model.response.sign


import com.google.gson.annotations.SerializedName

data class ResSocialLogin(
    val message: String,
    val status: Int,
    val success: Boolean,
    val data: Data,
) {
    data class Data(
        val accessToken: String,
        val refreshToken: String,
        val userName: String,
        val isSignup: Boolean
    )
}
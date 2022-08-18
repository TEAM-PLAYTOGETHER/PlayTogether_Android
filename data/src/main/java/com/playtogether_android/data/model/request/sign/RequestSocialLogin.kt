package com.playtogether_android.data.model.request.sign

data class RequestSocialLogin(
    val accessToken: String,
    val fcmToken: String,
)
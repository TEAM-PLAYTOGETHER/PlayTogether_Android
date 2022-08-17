package com.playtogether_android.domain.model.sign

data class SocialLoginData(
    val accessToken: String,
    val refreshToken: String,
    val userName: String
)

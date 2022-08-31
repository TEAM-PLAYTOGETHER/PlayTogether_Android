package com.playtogether_android.domain.model.sign

data class SocialLoginData(
    val userName: String,
    val accessToken: String,
    val refreshToken: String,
    val isSignup: Boolean
)

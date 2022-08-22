package com.playtogether_android.domain.model.sign

data class SocialLoginItem(
    val platform: String,
    val accessToken: String,
    val fcmToken: String
)

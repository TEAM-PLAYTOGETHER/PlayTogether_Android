package com.playtogether_android.domain.model.sign

data class IssuanceItem(
    val status: Int,
    val accessToken: String,
    val refreshToken: String
)

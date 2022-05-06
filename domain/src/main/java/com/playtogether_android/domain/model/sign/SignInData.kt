package com.playtogether_android.domain.model.sign

data class SignInData(
    val jwtToken: String,
    val userLoginId: String,
    val userName: String
)

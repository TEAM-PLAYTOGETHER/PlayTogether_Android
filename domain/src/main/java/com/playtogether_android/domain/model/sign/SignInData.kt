package com.playtogether_android.domain.model.sign

data class SignInData(
    var success: Boolean,
    val jwtToken: String,
    val userLoginId: String,
    val userName: String
)

package com.playtogether_android.data.model.request.sign

data class RequestSignUp(
    val userLoginId: String,
    val password: String,
    val userName: String,
    val gender: String,
    val birth: String,
    val mbti: String
)
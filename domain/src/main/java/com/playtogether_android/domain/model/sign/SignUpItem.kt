package com.playtogether_android.domain.model.sign

data class SignUpItem(
    val userLoginId: String,
    val password: String,
    val userName: String,
    val gender: String,
    val birth: String,
    val mbti: String
)

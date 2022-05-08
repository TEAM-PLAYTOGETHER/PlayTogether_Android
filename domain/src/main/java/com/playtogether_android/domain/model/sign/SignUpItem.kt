package com.playtogether_android.domain.model.sign

data class SignUpItem(
    var userLoginId: String,
    var password: String,
    var userName: String,
    var gender: String,
    var birth: String,
    val mbti: String
)

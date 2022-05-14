package com.playtogether_android.data.model.response.mypage

data class ResponseUserCheck(
    val data: Data,
    val message: String,
    val status: Int,
    val success: Boolean
) {
    data class Data(
        val age: Int,
        val mbti: String?,
        val name: String,
        val gender: String,
        val userLoginId: String
    )
}
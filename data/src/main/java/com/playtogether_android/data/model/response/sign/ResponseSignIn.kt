package com.playtogether_android.data.model.response.sign

data class ResponseSignIn(
    val data: Data,
    val message: String,
    val status: Int,
    val success: Boolean
) {
    data class Data(
        val jwtToken: String,
        val userLoginId: String,
        val userName: String
    )
}
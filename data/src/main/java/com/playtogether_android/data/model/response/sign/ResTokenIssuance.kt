package com.playtogether_android.data.model.response.sign

data class ResTokenIssuance(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: Data
) {
    data class Data(
        val accessToken: String,
        val refreshToken: String
    )
}

package com.playtogether_android.data.model.response.sign

data class ResponseSignId(
    val data: Data,
    val message: String,
    val status: Int,
    val success: Boolean
) {
    data class Data(
        val isUser: Boolean
    )
}
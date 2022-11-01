package com.playtogether_android.data.model.response.onboarding

data class ResponseCheckExist(
    val data: Data,
    val message: String,
    val status: Int,
    val success: Boolean
) {
    data class Data(
        val available: Boolean,
        val id: Int,
        val message: String,
        val name: String
    )
}
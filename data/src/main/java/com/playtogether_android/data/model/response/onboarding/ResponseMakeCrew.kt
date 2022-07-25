package com.playtogether_android.data.model.response.onboarding

data class ResponseMakeCrew(
    val data: Data,
    val message: String,
    val status: Int,
    val success: Boolean
) {
    data class Data(
        val code: String,
        val id: Int,
        val name: String
    )
}
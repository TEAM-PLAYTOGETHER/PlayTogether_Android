package com.playtogether_android.data.model.response.onboarding

data class ResponseRegisterCrew(
    val data: Data,
    val message: String,
    val status: Int,
    val success: Boolean
) {
    data class Data(
        val crewName: String
    )
}
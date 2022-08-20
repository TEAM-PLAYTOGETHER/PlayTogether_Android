package com.playtogether_android.data.model.request.onboarding

data class RequestAddProfile(
    val description: String,
    val firstStation: String?,
    val nickname: String,
    val secondStation: String?
)
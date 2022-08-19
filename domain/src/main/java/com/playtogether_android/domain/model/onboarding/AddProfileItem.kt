package com.playtogether_android.domain.model.onboarding

data class AddProfileItem(
    val description: String,
    val firstStation: String?,
    val nickname: String,
    val secondStation: String?
)
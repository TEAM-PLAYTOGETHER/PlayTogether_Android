package com.playtogether_android.domain.model.onboarding

data class NickNameDuplicationData(
    val message: String,
    val status: Int,
    val success: Boolean
)
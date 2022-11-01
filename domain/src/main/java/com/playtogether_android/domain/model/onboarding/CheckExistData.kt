package com.playtogether_android.domain.model.onboarding

data class CheckExistData(
    val available: Boolean,
    val id: Int,
    val message: String,
    val name: String
)

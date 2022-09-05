package com.playtogether_android.domain.model.onboarding

data class RegisterCrewData(
    val success: Boolean,
    val message: String,
    val crewId : Int,
    val crewName: String
)

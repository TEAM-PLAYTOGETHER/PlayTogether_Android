package com.playtogether_android.domain.model.thunder

data class GetThunderCreateData(
    val message: String,
    val status: Int,
    val success: Boolean,
    val lightId: Int,
    val crewId: Int,
)

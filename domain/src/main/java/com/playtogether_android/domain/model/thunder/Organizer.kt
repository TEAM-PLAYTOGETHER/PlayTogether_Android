package com.playtogether_android.domain.model.thunder

import com.google.gson.annotations.SerializedName

data class Organizer(
    val name: String,
    @SerializedName("organizer_id")
    val organizerId: Int,
    val userLoginId: String
)

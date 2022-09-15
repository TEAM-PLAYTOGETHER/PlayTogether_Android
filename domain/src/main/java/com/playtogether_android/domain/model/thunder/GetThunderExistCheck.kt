package com.playtogether_android.domain.model.thunder

import com.google.gson.annotations.SerializedName

data class GetThunderExistCheck(
    @SerializedName("is_entered")
    val isEntered: Boolean,
    @SerializedName("is_organizer")
    val isOrganizer: Boolean
)
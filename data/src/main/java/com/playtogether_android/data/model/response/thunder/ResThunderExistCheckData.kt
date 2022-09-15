package com.playtogether_android.data.model.response.thunder


import com.google.gson.annotations.SerializedName

data class ResThunderExistCheckData(
    val `data`: Data,
    val message: String,
    val status: Int,
    val success: Boolean
) {
    data class Data(
        @SerializedName("is_entered")
        val isEntered: Boolean,
        @SerializedName("is_organizer")
        val isOrganizer: Boolean
    )
}
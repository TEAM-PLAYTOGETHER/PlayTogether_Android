package com.playtogether_android.domain.model


import com.google.gson.annotations.SerializedName

data class GenericData(
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String
)
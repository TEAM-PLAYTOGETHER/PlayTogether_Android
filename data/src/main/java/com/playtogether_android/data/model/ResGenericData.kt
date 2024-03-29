package com.playtogether_android.data.model


import com.google.gson.annotations.SerializedName

data class ResGenericData(
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String
)
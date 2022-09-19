package com.playtogether_android.data.model.response.userInfo


import com.google.gson.annotations.SerializedName

data class ResUnblockUserData(
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String
)
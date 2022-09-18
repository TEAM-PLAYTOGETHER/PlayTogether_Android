package com.playtogether_android.domain.model.userInfo

import com.google.gson.annotations.SerializedName

data class BlockUserData(
    @SerializedName("id")
    val id: Int,
    @SerializedName("userId")
    val userId: Int,
    @SerializedName("blockUserId")
    val blockUserId: Int,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("updatedAt")
    val updatedAt: String
)



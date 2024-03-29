package com.playtogether_android.data.model.response.userInfo


import com.google.gson.annotations.SerializedName

data class ResBlockUserList(
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: List<Block>
) {
    data class Block(
        @SerializedName("userId")
        val userId: Int,
        @SerializedName("blockUserId")
        val blockUserId: Int,
        @SerializedName("name")
        val name: String
    )
}
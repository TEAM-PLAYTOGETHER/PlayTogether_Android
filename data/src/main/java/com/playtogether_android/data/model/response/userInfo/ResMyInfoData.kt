package com.playtogether_android.data.model.response.userInfo


import com.google.gson.annotations.SerializedName

data class ResMyInfoData(
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: Data
) {
    data class Data(
        @SerializedName("id")
        val id: String,
        @SerializedName("isDeleted")
        val isDeleted: Boolean,
        @SerializedName("nickname")
        val nickname: String?,
        @SerializedName("description")
        val description: String?,
        @SerializedName("firstStation")
        val firstStation: String?,
        @SerializedName("secondStation")
        val secondStation: String?,
        @SerializedName("profileImage")
        val profileImage: String?,
        @SerializedName("gender")
        val gender: String,
        @SerializedName("birth")
        val birth: String
    )
}
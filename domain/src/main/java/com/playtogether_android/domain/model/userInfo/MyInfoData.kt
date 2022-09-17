package com.playtogether_android.domain.model.userInfo

import com.google.gson.annotations.SerializedName

data class MyInfoData(
    @SerializedName("id")
    val id: String,
    val crewName: String,
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

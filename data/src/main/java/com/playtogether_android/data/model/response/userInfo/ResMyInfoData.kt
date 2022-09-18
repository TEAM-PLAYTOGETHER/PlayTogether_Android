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
        val crewName: String,
        val profile: Profile
    ) {
        data class Profile(
            val birth: String,
            val description: String?,
            val firstStation: String?,
            val gender: String,
            val id: String,
            val isDeleted: Boolean,
            val nickname: String?,
            val profileImage: String?,
            val secondStation: String?
        )
    }
}

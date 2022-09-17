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
//
//data class Data(
//    @SerializedName("id")
//    val id: String,
//    @SerializedName("isDeleted")
//    val isDeleted: Boolean,
//    @SerializedName("nickname")
//    val nickname: String? = null,
//    @SerializedName("description")
//    val description: String? = null,
//    @SerializedName("firstStation")
//    val firstStation: String? = null,
//    @SerializedName("secondStation")
//    val secondStation: String? = null,
//    @SerializedName("profileImage")
//    val profileImage: String? = null,
//    @SerializedName("gender")
//    val gender: String,
//    @SerializedName("birth")
//    val birth: String
//)
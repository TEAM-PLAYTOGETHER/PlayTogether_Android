package com.playtogether_android.app.presentation.ui.thunder


import com.google.gson.annotations.SerializedName

data class TempApplicantData(
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("userList")
    val data: List<UserList>
) {
    data class UserList(
        val userId: String,
        val age: Int,
        val mbti: String
    )
}
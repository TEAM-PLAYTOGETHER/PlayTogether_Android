package com.playtogether_android.domain.model.thunder

import com.google.gson.annotations.SerializedName

data class Member(
    val age: Int,
    val gender: String,
    val mbti: String,
    val name: String,
    @SerializedName("user_id")
    val userId: Int
)

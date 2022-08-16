package com.playtogether_android.data.model.request.thunder

import com.google.gson.annotations.SerializedName

data class RequestThunderCreate(
    val title: String,
    val category: String,
    val date: String,
    @SerializedName("people_cnt")
    val peopleCnt: Int,
    val description: String,
    val time: String,
    val place: String,
    val image: List<String>
)

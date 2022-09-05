package com.playtogether_android.domain.model.thunder

import com.google.gson.annotations.SerializedName

data class ThunderDetailData(
    val category: String,
    val date: String,
    val description: String,
    val image: String,
    @SerializedName("is_opened")
    val isOpened: Boolean,
    @SerializedName("light_id")
    val lightId: Int,
    @SerializedName("LightMemberCnt")
    val lightMemberCnt: Int,
    @SerializedName("people_cnt")
    val peopleCnt: Int,
    val place: String,
    @SerializedName("scp_cnt")
    val scpCnt: Int,
    val time: String,
    val title: String
)


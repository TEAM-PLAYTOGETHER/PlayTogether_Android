package com.playtogether_android.domain.model.thunder

import com.google.gson.annotations.SerializedName

data class ThunderTempDetailData(
    val category: String,
    val date: String,
    val description: String,
    @SerializedName("light_id")
    val lightId: Int,
    @SerializedName("LightMemberCnt")
    val lightMemberCnt: Int,
    @SerializedName("people_cnt")
    val peopleCnt: Int,
    val place: String,
    val time: String,
    val title: String
)


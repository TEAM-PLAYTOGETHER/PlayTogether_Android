package com.playtogether_android.app.presentation.ui.thunder


import com.google.gson.annotations.SerializedName

data class TempThunderListData(
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: List<ThunderList>
) {
    data class ThunderList(
        @SerializedName("light_id")
        val lightId: String,
        @SerializedName("title")
        val title: String,
        @SerializedName("date")
        val date: String,
        @SerializedName("time")
        val time: String,
        @SerializedName("people_cnt")
        val peopleCnt: Int,
        @SerializedName("place")
        val place: String,
        @SerializedName("LightMemberCnt")
        val lightMemberCnt: String
    )
}
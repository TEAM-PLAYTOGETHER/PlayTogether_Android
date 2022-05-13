package com.playtogether_android.domain.model.thunder

import com.google.gson.annotations.SerializedName

data class ThunderTabListData(
    val data: List<Data>
) {
    data class Data(
        @SerializedName("light_id")
        val lightId: Int,
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
        val lightMemberCnt: Int

    )
}


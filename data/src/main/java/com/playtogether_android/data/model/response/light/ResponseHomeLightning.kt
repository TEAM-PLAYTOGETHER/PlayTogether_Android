package com.playtogether_android.data.model.response.light


import com.google.gson.annotations.SerializedName

data class ResponseHomeLightning(
    val message: String,
    val status: Int,
    val success: Boolean,
    val data: List<Data>,
) {
    data class Data(
        val category: String,
        val date: String,
        @SerializedName("light_id")
        val lightId: Int,
        @SerializedName("LightMemberCnt")
        val lightMemberCnt: Int,
        @SerializedName("people_cnt")
        val peopleCnt: Int,
        val place: String,
        val time: String,
        val title: String,
        @SerializedName("is_opened")
        val open: Boolean,
        @SerializedName("scp_cnt")
        val scpCnt: Int
    )
}
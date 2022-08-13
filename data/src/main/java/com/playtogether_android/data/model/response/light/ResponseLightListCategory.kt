package com.playtogether_android.data.model.response.light


import com.google.gson.annotations.SerializedName

data class ResponseLightListCategory(
    val message: String,
    val status: Int,
    val success: Boolean,
    val `data`: Data,
) {
    data class Data(
        val totalCount: Int,
        val totalPage: Int,
        val lightData: List<LightData>,
    ) {
        data class LightData(
            val category: String,
            val date: String,
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
    }
}
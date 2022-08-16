package com.playtogether_android.data.model.response.light


import com.google.gson.annotations.SerializedName

data class ResponseLightListCategory(
    val message: String,
    val status: Int,
    val success: Boolean,
    val `data`: Data,
) {
    data class Data(
        val lightData: List<LightData>,
        val limit: Int,
        val offset: Int,
        val totalCount: Int,
        val totalPage: Int,
    ) {
        data class LightData(
            val category: String,
            val date: String,
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
    }
}
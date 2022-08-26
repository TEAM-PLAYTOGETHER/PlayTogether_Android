package com.playtogether_android.domain.model.search

import com.google.gson.annotations.SerializedName

data class SearchData(
    val lightData: List<LightData>,
    val limit: Int,
    val offset: Int,
    val totalCount: Int,
    val totalPage: Int
){
    data class LightData(
        val lightMemberCnt: Int,
        val category: String,
        val date: String,
        @SerializedName("light_id")
        val lightId: Int,
        @SerializedName("people_cnt")
        val peopleCnt: Int,
        val place: String,
        @SerializedName("scp_cnt")
        val scpCnt: Int,
        val time: String,
        val title: String,
        @SerializedName("is_opened")
        val open : Boolean
    )
}

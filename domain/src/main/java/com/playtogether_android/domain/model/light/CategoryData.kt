package com.playtogether_android.domain.model.light

import com.google.gson.annotations.SerializedName

data class CategoryData(
    val category: String,
    val date: String,
    @SerializedName("light_id")
    val lightId: String,
    @SerializedName("LightMemberCnt")
    val lightMemberCnt: String,
    @SerializedName("people_cnt")
    val peopleCnt: Int,
    val place: String,
    val time: String,
    val title: String
)

data class TestData(
    val list: List<CategoryData>
)
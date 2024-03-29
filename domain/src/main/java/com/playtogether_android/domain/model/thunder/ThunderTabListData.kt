package com.playtogether_android.domain.model.thunder

import com.google.gson.annotations.SerializedName
import com.playtogether_android.domain.model.light.CategoryData

data class ThunderTabListData(
    val data: List<CategoryData>
)
//) {
//    data class CategoryData(
//        @SerializedName("light_id")
//        val lightId: Int,
//        @SerializedName("title")
//        val title: String,
//        val category: String,
//        @SerializedName("scp_cnt")
//        val scpCnt: Int,
//        @SerializedName("date")
//        val date: String,
//        @SerializedName("time")
//        val time: String,
//        @SerializedName("people_cnt")
//        val peopleCnt: Int,
//        @SerializedName("place")
//        val place: String,
//        @SerializedName("LightMemberCnt")
//        val lightMemberCnt: Int,
//        @SerializedName("is_opened")
//        val isOpen: Boolean,
//    )
//}


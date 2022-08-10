package com.playtogether_android.domain.model.light

data class HomeLightningData(
    val id: Int,
    val title: String,
    val category: String,
    val peopleCnt: Int,
    val lightMemberCnt: Int,
    val place: String,
    val time: String,
    val date: String
)


package com.playtogether_android.domain.model.thunder

data class PostThunderCreateData(
    val title: String,
    val category: String,
    val date: String,
    val time: String,
    val place: String,
    val peopleCnt: Int,
    val description: String,
    val image: List<String>
)
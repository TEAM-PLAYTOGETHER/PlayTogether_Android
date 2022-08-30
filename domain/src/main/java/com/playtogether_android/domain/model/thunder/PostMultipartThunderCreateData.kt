package com.playtogether_android.domain.model.thunder

data class PostMultipartThunderCreateData(
    val category: String,
    val createdAt: String,
    val crewId: Int,
    val date: String,
    val description: String,
    val id: Int,
    val image: List<String>,
    val isDeleted: Boolean,
    val organizerId: Int,
    val peopleCnt: Int,
    val place: String,
    val time: String,
    val title: String,
    val updatedAt: String,
)

package com.playtogether_android.data.model.response.thunder

data class ResponseThunderCreate(
    val data: List<Data>,
    val message: String,
    val status: Int,
    val success: Boolean
){
    data class Data(
        val id: Int,
        val category: String,
        val title: String,
        val date: String,
        val place: String,
        val peopleCnt: Int,
        val description: String,
        val isDeleted: Boolean,
        val createdAt: String,
        val updatedAt: String,
        val organizerId: Int,
        val crewId: Int,
        val time: String,

    )
}
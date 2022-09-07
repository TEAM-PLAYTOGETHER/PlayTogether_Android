package com.playtogether_android.data.model.response.thunder

data class ResThunderCreateSingle(
    val data: List<Data>,
    val message: String,
    val status: Int,
    val success: Boolean
) {
    data class Data(
        val category: String,
        val createdAt: String,
        val crewId: Int,
        val date: String,
        val description: String,
        val id: Int,
        val image: String,
        val isDeleted: Boolean,
        val organizerId: Int,
        val peopleCnt: Int,
        val place: String,
        val time: String,
        val title: String,
        val updatedAt: String
    )
}
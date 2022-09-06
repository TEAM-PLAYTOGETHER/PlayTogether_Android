package com.playtogether_android.data.model.response.thunder


import com.google.gson.annotations.SerializedName

data class ResThunderChangeData(
    val message: String,
    val status: Int,
    val success: Boolean,
    val `data`: Data,
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
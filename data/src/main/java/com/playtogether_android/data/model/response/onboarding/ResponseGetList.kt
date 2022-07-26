package com.playtogether_android.data.model.response.onboarding

data class ResponseGetList(
    val data: Data,
    val message: String,
    val status: Int,
    val success: Boolean
) {
    data class Data(
        val crewList: List<CrewList>
    ) {
        data class CrewList(
            val id: Int,
            val name: String,
            val description: String?
        )
    }
}
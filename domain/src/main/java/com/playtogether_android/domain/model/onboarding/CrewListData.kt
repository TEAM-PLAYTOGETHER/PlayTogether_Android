package com.playtogether_android.domain.model.onboarding

data class CrewListData(
    val data: Data,
    val success: Boolean
) {
    data class Data(
        val crewList: List<CrewList>
    ) {
        data class CrewList(
            val id: Int,
            val name: String
        )
    }
}
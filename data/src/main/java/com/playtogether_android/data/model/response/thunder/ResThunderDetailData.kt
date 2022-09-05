package com.playtogether_android.data.model.response.thunder


import com.google.gson.annotations.SerializedName

data class ResThunderDetailData(
    val message: String,
    val status: Int,
    val success: Boolean,
    val data: List<Data>,
) {
    data class Data(
        val category: String,
        val date: String,
        val description: String,
        val image: String,
        @SerializedName("is_opened")
        val isOpened: Boolean,
        @SerializedName("light_id")
        val lightId: Int,
        @SerializedName("LightMemberCnt")
        val lightMemberCnt: Int,
        val members: List<Member>,
        val organizer: List<Organizer>,
        @SerializedName("people_cnt")
        val peopleCnt: Int,
        val place: String,
        @SerializedName("scp_cnt")
        val scpCnt: Int,
        val time: String,
        val title: String
    ) {
        data class Member(
            val age: Int,
            val gender: String,
            val name: String,
            @SerializedName("user_id")
            val userId: Int
        )

        data class Organizer(
            val name: String,
            @SerializedName("organizer_id")
            val organizerId: Int
        )
    }
}
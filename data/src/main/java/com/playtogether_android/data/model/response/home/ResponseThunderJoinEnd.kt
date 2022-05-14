package com.playtogether_android.data.model.response.home

data class ResponseThunderJoinEnd(
    val data: List<Data>,
    val message: String,
    val status: Int,
    val success: Boolean
) {
    data class Data(
        val LightMemberCnt: Int,
        val category: String,
        val date: String,
        val description: String,
        val light_id: Int,
        val members: List<Member>,
        val organizer: List<Organizer>,
        val people_cnt: Any,
        val place: String,
        val time: String,
        val title: String
    ) {
        data class Member(
            val age: Int,
            val gender: String,
            val mbti: String,
            val name: String,
            val user_id: Int
        )

        data class Organizer(
            val name: String,
            val organizer_id: Int
        )
    }
}
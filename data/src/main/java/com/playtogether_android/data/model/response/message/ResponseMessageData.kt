package com.playtogether_android.data.model.response.message

data class ResponseMessageData(
    val data: Data,
    val message: String,
    val status: Int,
    val success: Boolean
) {
    data class Data(
        val messages: List<Message>
    ) {
        data class Message(
            val audience: String,
            val audienceId: Int,
            val content: String,
            val createdAt: String,
            val read: Boolean,
            val roomId: Int,
            val send: Boolean
        )
    }
}

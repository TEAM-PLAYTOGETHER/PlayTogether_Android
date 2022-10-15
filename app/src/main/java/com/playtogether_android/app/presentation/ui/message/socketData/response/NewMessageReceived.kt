package com.playtogether_android.app.presentation.ui.message.socketData.response

data class NewMessageReceived(
    val data: Data,
    val message: String,
    val status: Int,
    val success: Boolean
) {
    data class Data(
        val message: Message
    ) {
        data class Message(
            val audience: String,
            val audienceId: Int,
            val audienceProfile: String,
            val content: String,
            var createdAt: String,
            val roomId: Int
        )
    }
}
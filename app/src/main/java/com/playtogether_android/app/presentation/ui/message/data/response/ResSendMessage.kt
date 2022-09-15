package com.playtogether_android.app.presentation.ui.message.data.response

data class ResSendMessage(
    val data: Data,
    val message: String,
    val status: Int,
    val success: Boolean
) {
    data class Data(
        val message: Message
    ) {
        data class Message(
            val content: String,
            val createdAt: String,
            val messageId: Int,
            val read: Boolean,
            val send: Boolean
        )
    }
}
package com.playtogether_android.data.model.response.message

data class ResponseChatData(
    val data: Data,
    val message: String,
    val status: Int,
    val success: Boolean
){
    data class Data(
        val messages: List<Message>
    ){
        data class Message(
            val content: String,
            val createdAt: String,
            val messageId: Int,
            val read: Boolean,
            val send: Boolean
        )
    }
}
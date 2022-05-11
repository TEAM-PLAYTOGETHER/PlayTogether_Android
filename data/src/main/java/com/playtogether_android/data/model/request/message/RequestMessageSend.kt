package com.playtogether_android.data.model.request.message

data class RequestMessageSend(
    val content: String,
    val recvId: Int
)
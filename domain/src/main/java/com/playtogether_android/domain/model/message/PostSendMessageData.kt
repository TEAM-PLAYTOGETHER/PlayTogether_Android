package com.playtogether_android.domain.model.message

data class PostSendMessageData(
    val content: String,
    val recvId: Int
)
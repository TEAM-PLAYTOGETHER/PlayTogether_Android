package com.playtogether_android.domain.repository.message

import com.playtogether_android.domain.model.message.MessageData

interface MessageRepository {
    suspend fun getMessageData() : List<MessageData>
}
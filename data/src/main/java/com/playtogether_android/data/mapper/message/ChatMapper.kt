package com.playtogether_android.data.mapper.message

import com.playtogether_android.data.model.response.message.ResponseChatData
import com.playtogether_android.domain.model.message.ChatData

object ChatMapper {
    fun mapperToDomainChat(data: ResponseChatData): List<ChatData> {
        return data.data.messages.map {
            ChatData(
                content = it.content,
                time = it.createdAt,
                messageType = it.send,
                messageId = it.messageId
            )
        }
    }
}
package com.playtogether_android.data.mapper.message

import com.playtogether_android.data.model.response.message.ResponseChatData
import com.playtogether_android.domain.model.message.ChatData2

object ChatMapper {
    fun mapperToDomainChat(data: ResponseChatData): List<ChatData2> {
        return data.data.messages.map {
            ChatData2(
                content = it.content,
                time = it.createdAt,
                messageType = it.send,
                messageId = it.messageId
            )
        }
    }
}
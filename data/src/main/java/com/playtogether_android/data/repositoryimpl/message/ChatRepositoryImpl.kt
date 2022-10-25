package com.playtogether_android.data.repositoryimpl.message

import com.playtogether_android.data.datasource.message.ChatDataSource
import com.playtogether_android.data.mapper.message.ChatMapper
import com.playtogether_android.domain.model.message.ChatData2
import com.playtogether_android.domain.repository.message.ChatRepository

class ChatRepositoryImpl(private val chatDataSource: ChatDataSource) : ChatRepository {
    override suspend fun getChatData(roomId: Int, messageId: Int?, pageSize: Int): List<ChatData2> {
        return ChatMapper.mapperToDomainChat(
            chatDataSource.getChatData(roomId, messageId, pageSize)
        )
    }
}
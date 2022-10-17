package com.playtogether_android.data.repositoryimpl.message

import com.playtogether_android.data.datasource.message.ChatDataSource
import com.playtogether_android.data.mapper.message.ChatMapper
import com.playtogether_android.domain.model.message.ChatData
import com.playtogether_android.domain.repository.message.ChatRepository

class ChatRepositoryImpl(private val chatDataSource: ChatDataSource) : ChatRepository {
    override suspend fun getChatData(roomId: Int, curPage: Int, pageSize: Int): List<ChatData> {
        return ChatMapper.mapperToDomainChat(chatDataSource.getChatData(roomId, curPage, pageSize))
    }
}
package com.playtogether_android.data.repositoryimpl.message

import com.playtogether_android.data.datasource.message.MessageDataSource
import com.playtogether_android.data.mapper.message.MessageMapper
import com.playtogether_android.domain.model.message.MessageData
import com.playtogether_android.domain.repository.message.MessageRepository

class MessageRepositoryImpl(private val messageDataSource: MessageDataSource) : MessageRepository {

    override suspend fun getMessageData(): List<MessageData> {
        return MessageMapper.mapperToDomainMessage(messageDataSource.getMessageData().data)
    }
}
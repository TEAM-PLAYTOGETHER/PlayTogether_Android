package com.playtogether_android.data.repositoryimpl.message

import com.playtogether_android.data.datasource.message.SendMessageDataSource
import com.playtogether_android.data.mapper.message.SendMessageMapper
import com.playtogether_android.domain.model.message.GetSendMessageData
import com.playtogether_android.domain.model.message.PostSendMessageData
import com.playtogether_android.domain.repository.message.MessageSendReposiotry

class SendMessageRepositoryImpl(private val sendMessageDataSource: SendMessageDataSource) : MessageSendReposiotry {
    override suspend fun postSendMessage(postSendMessageData: PostSendMessageData): GetSendMessageData {
        return SendMessageMapper.mapperToGetSendMessageData(sendMessageDataSource.postSendMessage(
            SendMessageMapper.mapperToPostSendMessageData(postSendMessageData)
        ))
    }

}
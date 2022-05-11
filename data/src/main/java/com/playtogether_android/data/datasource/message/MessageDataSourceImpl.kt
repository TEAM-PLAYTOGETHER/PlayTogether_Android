package com.playtogether_android.data.datasource.message

import com.playtogether_android.data.api.message.MessageService
import com.playtogether_android.data.model.response.message.ResponseMessageData

class MessageDataSourceImpl(private val service : MessageService) : MessageDataSource {
    override suspend fun getMessageData(): ResponseMessageData {
        return service.getMessageData()
    }
}
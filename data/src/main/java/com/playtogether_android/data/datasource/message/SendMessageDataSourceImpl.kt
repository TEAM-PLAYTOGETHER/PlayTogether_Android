package com.playtogether_android.data.datasource.message

import com.playtogether_android.data.api.message.MessageSendService
import com.playtogether_android.data.model.request.message.RequestMessageSend
import com.playtogether_android.data.model.response.message.ResponseMessageSend

class SendMessageDataSourceImpl(private val service : MessageSendService) : SendMessageDataSource {
    override suspend fun postSendMessage(requestMessageSend: RequestMessageSend): ResponseMessageSend {
        return service.postMessageSend(requestMessageSend)
    }

}
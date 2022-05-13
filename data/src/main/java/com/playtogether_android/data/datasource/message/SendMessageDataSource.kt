package com.playtogether_android.data.datasource.message

import com.playtogether_android.data.model.request.message.RequestMessageSend
import com.playtogether_android.data.model.response.message.ResponseMessageSend

interface SendMessageDataSource {
    suspend fun postSendMessage(requestMessageSend: RequestMessageSend) : ResponseMessageSend
}
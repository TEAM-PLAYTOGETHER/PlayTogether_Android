package com.playtogether_android.data.datasource.message

import com.playtogether_android.data.api.message.ChatService
import com.playtogether_android.data.model.response.message.ResponseChatData

class ChatDataSourceImpl(private val service : ChatService) : ChatDataSource {
    override suspend fun getChatData(roomId : Int): ResponseChatData {
        return service.getChatData(roomId)
    }

}
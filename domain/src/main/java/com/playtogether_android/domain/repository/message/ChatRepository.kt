package com.playtogether_android.domain.repository.message

import com.playtogether_android.domain.model.message.ChatData2

interface ChatRepository {
    suspend fun getChatData(roomId: Int, messageId: Int?, pageSize: Int): List<ChatData2>
}
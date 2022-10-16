package com.playtogether_android.domain.repository.message

import com.playtogether_android.domain.model.message.ChatData

interface ChatRepository {
    suspend fun getChatData(roomId: Int, curPage: Int, pageSize: Int): List<ChatData>
}
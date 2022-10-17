package com.playtogether_android.data.datasource.message

import com.playtogether_android.data.model.response.message.ResponseChatData

interface ChatDataSource {
    suspend fun getChatData(roomId: Int, curPage: Int, pageSize: Int): ResponseChatData
}
package com.playtogether_android.data.api.message

import com.playtogether_android.data.model.response.message.ResponseChatData
import retrofit2.http.GET
import retrofit2.http.Path

interface ChatService {
    @GET("message/{roomId}")
    suspend fun getChatData(
        @Path("roomId") roomId: Int
    ): ResponseChatData
}
package com.playtogether_android.data.api.message

import com.playtogether_android.data.model.response.message.ResponseChatData
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ChatService {
    @GET("message/{roomId}")
    suspend fun getChatData(
        @Path("roomId") roomId: Int,
        @Query("curPage") curPage : Int,
        @Query("pageSize") pageSize : Int
    ): ResponseChatData
}
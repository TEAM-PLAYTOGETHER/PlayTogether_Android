package com.playtogether_android.data.api.message

import com.playtogether_android.data.model.response.message.ResponseRoomIdData
import retrofit2.http.GET
import retrofit2.http.Query

interface RoomIdService {
    @GET("message/room-exist")
    suspend fun getRoomId(
        @Query("recvId") recvId : Int
    ): ResponseRoomIdData
}
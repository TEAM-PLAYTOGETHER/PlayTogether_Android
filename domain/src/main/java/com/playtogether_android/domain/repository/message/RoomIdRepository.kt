package com.playtogether_android.domain.repository.message

import com.playtogether_android.domain.model.message.RoomIdData

interface RoomIdRepository {
    suspend fun getRoomId(recvId : Int) : RoomIdData
}
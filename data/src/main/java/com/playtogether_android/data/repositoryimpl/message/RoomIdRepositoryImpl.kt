package com.playtogether_android.data.repositoryimpl.message

import com.playtogether_android.data.api.message.RoomIdService
import com.playtogether_android.domain.model.message.RoomIdData
import com.playtogether_android.domain.repository.message.RoomIdRepository

class RoomIdRepositoryImpl(private val service: RoomIdService) : RoomIdRepository {
    override suspend fun getRoomId(recvId: Int): RoomIdData {
        return service.getRoomId(recvId).let { RoomIdData(roomId = it.data.roomId.toInt()) }
    }
}
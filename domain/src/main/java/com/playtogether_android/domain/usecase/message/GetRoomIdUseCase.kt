package com.playtogether_android.domain.usecase.message

import com.playtogether_android.domain.model.message.RoomIdData
import com.playtogether_android.domain.repository.message.RoomIdRepository
import javax.inject.Inject

class GetRoomIdUseCase @Inject constructor(private val repository : RoomIdRepository) {
    suspend operator fun invoke(recvId : Int): RoomIdData {
        return repository.getRoomId(recvId)
    }
}
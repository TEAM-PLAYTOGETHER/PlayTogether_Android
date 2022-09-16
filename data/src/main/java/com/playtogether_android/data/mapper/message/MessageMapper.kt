package com.playtogether_android.data.mapper.message

import com.playtogether_android.data.model.response.message.ResponseMessageData
import com.playtogether_android.domain.model.message.MessageData

object MessageMapper {
    fun mapperToDomainMessage(data: ResponseMessageData.Data): List<MessageData> {
        return data.messages.map {
            MessageData(
                audience = it.audience,
                audienceId = it.audienceId,
                audienceProfile = it.audienceProfile,
                content = it.content,
                createdAt = it.createdAt,
                read = it.read,
                roomId = it.roomId,
                send = it.send
            )
        }
    }
}
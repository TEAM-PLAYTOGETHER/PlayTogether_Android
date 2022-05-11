package com.playtogether_android.data.mapper.message

import com.playtogether_android.data.model.response.message.ResponseMessageData
import com.playtogether_android.domain.model.message.MessageData

object MessageMapper {
    fun mapperToDomainMessage(data: ResponseMessageData.Data): List<MessageData> {
        return data.messages.map {
            MessageData(
                it.audience,
                it.audienceId,
                it.content,
                it.createdAt,
                it.read,
                it.roomId,
                it.send
            )
        }
    }
}
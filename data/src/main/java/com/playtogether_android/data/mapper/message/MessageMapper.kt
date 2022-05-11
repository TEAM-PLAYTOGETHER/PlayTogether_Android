package com.playtogether_android.data.mapper.message

import com.playtogether_android.data.model.response.message.ResponseMessageData
import com.playtogether_android.domain.model.message.MessageData

object MessageMapper {
    fun mapperToDomainMessage(responseMessageData: ResponseMessageData): MessageData {
        return MessageData(
            success = responseMessageData.success,

        )
    }
}
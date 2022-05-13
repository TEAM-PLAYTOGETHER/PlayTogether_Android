package com.playtogether_android.data.mapper.message

import com.playtogether_android.data.model.request.message.RequestMessageSend
import com.playtogether_android.data.model.response.message.ResponseMessageSend
import com.playtogether_android.domain.model.message.GetSendMessageData
import com.playtogether_android.domain.model.message.PostSendMessageData

object SendMessageMapper {
    fun mapperToGetSendMessageData(responseMessageSend: ResponseMessageSend) : GetSendMessageData {
        return GetSendMessageData(
            roomId = responseMessageSend.data.roomId,
            success = responseMessageSend.success
        )
    }

    fun mapperToPostSendMessageData(postSendMessageData: PostSendMessageData) : RequestMessageSend{
        return RequestMessageSend(
            content = postSendMessageData.content,
            recvId = postSendMessageData.recvId
        )
    }
}
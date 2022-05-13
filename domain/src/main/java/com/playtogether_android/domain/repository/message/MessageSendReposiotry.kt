package com.playtogether_android.domain.repository.message

import com.playtogether_android.domain.model.message.GetSendMessageData
import com.playtogether_android.domain.model.message.PostSendMessageData

interface MessageSendReposiotry {
    suspend fun postSendMessage(postSendMessageData: PostSendMessageData) : GetSendMessageData
}
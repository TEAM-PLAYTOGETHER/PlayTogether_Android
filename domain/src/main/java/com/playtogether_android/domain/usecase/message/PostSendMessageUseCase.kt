package com.playtogether_android.domain.usecase.message

import com.playtogether_android.domain.model.message.GetSendMessageData
import com.playtogether_android.domain.model.message.PostSendMessageData
import com.playtogether_android.domain.repository.message.MessageSendReposiotry
import javax.inject.Inject

class PostSendMessageUseCase @Inject constructor(private val repository: MessageSendReposiotry) {
    suspend operator fun invoke(postSendMessageData: PostSendMessageData): GetSendMessageData {
        return repository.postSendMessage(postSendMessageData)
    }
}
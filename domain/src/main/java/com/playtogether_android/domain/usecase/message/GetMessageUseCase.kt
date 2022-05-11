package com.playtogether_android.domain.usecase.message

import com.playtogether_android.domain.model.message.MessageData
import com.playtogether_android.domain.repository.message.MessageRepository

class GetMessageUseCase(private val repository : MessageRepository) {
    suspend operator fun invoke() : List<MessageData>{
        return repository.getMessageData()
    }
}
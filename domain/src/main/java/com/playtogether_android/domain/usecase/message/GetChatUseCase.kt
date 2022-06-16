package com.playtogether_android.domain.usecase.message

import com.playtogether_android.domain.model.message.ChatData
import com.playtogether_android.domain.repository.message.ChatRepository
import javax.inject.Inject

class GetChatUseCase @Inject constructor(private val repository: ChatRepository) {
    suspend operator fun invoke(roomId: Int): List<ChatData> {
        return repository.getChatData(roomId)
    }
}
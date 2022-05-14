package com.playtogether_android.domain.usecase.thunder

import com.playtogether_android.domain.model.thunder.ThunderDeleteData
import com.playtogether_android.domain.repository.thunder.ThunderRepository

class PostThunderDeleteUseCase(private val thunderRepository: ThunderRepository) {
    suspend operator fun invoke(thunderId: Int): ThunderDeleteData {
        return thunderRepository.postThunderDelete(thunderId)
    }
}
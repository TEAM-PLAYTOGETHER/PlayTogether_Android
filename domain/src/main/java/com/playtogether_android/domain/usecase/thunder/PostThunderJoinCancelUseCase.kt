package com.playtogether_android.domain.usecase.thunder

import com.playtogether_android.domain.model.thunder.ThunderJoinCancel
import com.playtogether_android.domain.repository.thunder.ThunderRepository

class PostThunderJoinCancelUseCase(private val thunderRepository: ThunderRepository) {
    suspend operator fun invoke(thunderId: String): ThunderJoinCancel {
        return thunderRepository.postThunderJoinCancel(thunderId)
    }
}
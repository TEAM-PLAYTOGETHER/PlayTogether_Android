package com.playtogether_android.domain.usecase.thunder

import com.playtogether_android.domain.model.thunder.ThunderJoinCancelData
import com.playtogether_android.domain.repository.thunder.ThunderRepository
import javax.inject.Inject

class PostThunderJoinCancelUseCase @Inject constructor(private val thunderRepository: ThunderRepository) {
    suspend operator fun invoke(thunderId: Int): ThunderJoinCancelData {
        return thunderRepository.postThunderJoinCancel(thunderId)
    }
}
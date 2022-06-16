package com.playtogether_android.domain.usecase.thunder

import com.playtogether_android.domain.model.thunder.ThunderDeleteData
import com.playtogether_android.domain.repository.thunder.ThunderRepository
import javax.inject.Inject

class PostThunderDeleteUseCase @Inject constructor(private val thunderRepository: ThunderRepository) {
    suspend operator fun invoke(thunderId: Int): ThunderDeleteData {
        return thunderRepository.postThunderDelete(thunderId)
    }
}
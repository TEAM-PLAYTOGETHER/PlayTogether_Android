package com.playtogether_android.domain.usecase.thunder

import com.playtogether_android.domain.model.thunder.ThunderDetailData
import com.playtogether_android.domain.repository.thunder.ThunderRepository

class GetThunderDetailUseCase(private val repo: ThunderRepository) {
    suspend operator fun invoke(thunderId: Int): List<ThunderDetailData> {
        return repo.getThunderDetail(thunderId)
    }
}
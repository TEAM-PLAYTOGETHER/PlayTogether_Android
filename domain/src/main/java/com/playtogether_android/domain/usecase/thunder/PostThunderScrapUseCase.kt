package com.playtogether_android.domain.usecase.thunder

import com.playtogether_android.domain.repository.thunder.ThunderRepository
import javax.inject.Inject

class PostThunderScrapUseCase @Inject constructor(private val repository: ThunderRepository) {
    suspend operator fun invoke(thunderId: Int) {
        repository.postScrap(thunderId)
    }
}
package com.playtogether_android.domain.usecase.thunder

import com.playtogether_android.domain.model.thunder.GetThunderExistCheck
import com.playtogether_android.domain.repository.thunder.ThunderRepository
import javax.inject.Inject

class GetThunderExistCheckerUseCase @Inject constructor(private val repository: ThunderRepository) {
    suspend operator fun invoke(thunderId: Int): GetThunderExistCheck {
        return repository.getThunderExistCheck(thunderId)
    }
}
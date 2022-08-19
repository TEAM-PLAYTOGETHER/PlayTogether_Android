package com.playtogether_android.domain.usecase.thunder

import android.util.Log
import com.playtogether_android.domain.model.thunder.GetThunderCreateData
import com.playtogether_android.domain.model.thunder.PostThunderCreateData
import com.playtogether_android.domain.repository.thunder.ThunderCreateRepository
import javax.inject.Inject

class PostMultipartThunderCreateUseCase @Inject constructor(private val repository: ThunderCreateRepository) {
    suspend operator fun invoke(
        crewId: Int,
        postThunderCreateData: PostThunderCreateData
    ): GetThunderCreateData {
        return repository.postMultipartThunderCreate(crewId, postThunderCreateData)
    }
}
package com.playtogether_android.domain.repository.thunder

import com.playtogether_android.domain.model.thunder.GetThunderCreateData
import com.playtogether_android.domain.model.thunder.PostThunderCreateData

interface ThunderCreateRepository {
    suspend fun postThunderCreate(
        crewId: Int,
        postThunderCreateData: PostThunderCreateData
    ): GetThunderCreateData

    suspend fun postMultipartThunderCreate(
        crewId: Int,
        postThunderCreateData: PostThunderCreateData
    ): GetThunderCreateData
}
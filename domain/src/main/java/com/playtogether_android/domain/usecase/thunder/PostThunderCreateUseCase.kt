package com.playtogether_android.domain.usecase.thunder

import android.util.Log
import com.playtogether_android.domain.model.thunder.GetThunderCreateData
import com.playtogether_android.domain.model.thunder.PostThunderCreateData
import com.playtogether_android.domain.repository.thunder.ThunderCreateRepository
import javax.inject.Inject

class PostThunderCreateUseCase @Inject constructor(private val repository: ThunderCreateRepository) {
    suspend operator fun invoke(
        crewId: Int,
        postThunderCreateData: PostThunderCreateData
    ): GetThunderCreateData {
        val g: GetThunderCreateData = repository.postThunderCreate(crewId, postThunderCreateData)
        Log.d("createThunder", "usecase 호출됨")
        Log.d("createThunder", "${g.message}")
        return g
    }
}
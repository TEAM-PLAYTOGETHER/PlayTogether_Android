package com.playtogether_android.domain.repository.thunder

import com.playtogether_android.domain.model.thunder.GetThunderCreateData
import com.playtogether_android.domain.model.thunder.PostMultipartThunderCreateData
import com.playtogether_android.domain.model.thunder.PostThunderCreateData
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface ThunderCreateRepository {
    suspend fun postThunderCreate(
        crewId: Int,
        postThunderCreateData: PostThunderCreateData
    ): GetThunderCreateData

    suspend fun postMultipartThunderCreate(
        crewId: Int,
        body: HashMap<String, RequestBody>,
        image: MultipartBody.Part?
    )
}
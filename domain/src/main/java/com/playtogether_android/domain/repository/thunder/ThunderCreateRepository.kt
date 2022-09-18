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
        image: List<MultipartBody.Part?>,
        body: HashMap<String, RequestBody>
    ): GetThunderCreateData

    suspend fun postMultipartThunderCreateSingle(
        crewId: Int,
        image: MultipartBody.Part?,
        body: HashMap<String, RequestBody?>
    ): GetThunderCreateData
}
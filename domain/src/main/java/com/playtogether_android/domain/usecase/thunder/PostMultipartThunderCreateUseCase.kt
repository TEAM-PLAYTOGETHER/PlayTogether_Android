package com.playtogether_android.domain.usecase.thunder

import com.playtogether_android.domain.model.thunder.GetThunderCreateData
import com.playtogether_android.domain.model.thunder.PostThunderCreateData
import com.playtogether_android.domain.repository.thunder.ThunderCreateRepository
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject

class PostMultipartThunderCreateUseCase @Inject constructor(private val repository: ThunderCreateRepository) {
    suspend operator fun invoke(
        crewId: Int,
        body: HashMap<String, RequestBody>,
        image: MultipartBody.Part?
    ) {
        repository.postMultipartThunderCreate(crewId, body, image)
    }
}
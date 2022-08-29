package com.playtogether_android.data.datasource.thunder

import com.playtogether_android.data.model.request.thunder.RequestThunderCreate
import com.playtogether_android.data.model.response.thunder.ResponseThunderCreate
import com.playtogether_android.domain.model.thunder.PostThunderCreateData
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body

interface ThunderCreateDataSource {
    suspend fun postThunderCreate(
        crewId: Int,
        requestThunderCreate: RequestThunderCreate
    ): ResponseThunderCreate

    suspend fun postMultipartThunderCreate(
        crewId: Int,
        image: List<MultipartBody.Part?>,
        body: HashMap<String,RequestBody>
    ): ResponseThunderCreate
}
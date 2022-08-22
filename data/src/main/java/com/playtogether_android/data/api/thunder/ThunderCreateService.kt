package com.playtogether_android.data.api.thunder

import com.playtogether_android.data.model.request.thunder.RequestThunderCreate
import com.playtogether_android.data.model.response.thunder.ResponseThunderCreate
import com.playtogether_android.domain.model.thunder.PostThunderCreateData
import okhttp3.MultipartBody
import retrofit2.http.*

interface ThunderCreateService {
    @POST("light/add/{crewId}")
    suspend fun postThunderCreate(
        @Path("crewId") crewId: Int,
        @Body requestThunderCreate: RequestThunderCreate
    ): ResponseThunderCreate

    @POST("light/add/{crewId}")
    suspend fun postMultipartThunderCreate(
        @Path("crewId") crewId: Int,
        @Body requestThunderCreate: RequestThunderCreate,
    ): ResponseThunderCreate
}
package com.playtogether_android.data.api.sign

import com.playtogether_android.data.model.request.sign.RequestSignId
import com.playtogether_android.data.model.response.sign.ResponseSignId
import retrofit2.http.Body
import retrofit2.http.POST

interface SignService {
    @POST("auth/usercheck")
    suspend fun postSignId(
        @Body requestSignId : RequestSignId
    ): ResponseSignId
}
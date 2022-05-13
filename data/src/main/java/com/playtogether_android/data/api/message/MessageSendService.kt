package com.playtogether_android.data.api.message

import com.playtogether_android.data.model.request.message.RequestMessageSend
import com.playtogether_android.data.model.response.message.ResponseMessageSend
import retrofit2.http.Body
import retrofit2.http.POST

interface MessageSendService {
    @POST("message")
    suspend fun postMessageSend(
        @Body requestMessageSend: RequestMessageSend
    ) : ResponseMessageSend
}
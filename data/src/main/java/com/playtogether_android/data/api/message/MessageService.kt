package com.playtogether_android.data.api.message

import com.playtogether_android.data.model.response.message.ResponseMessageData
import retrofit2.http.GET

interface MessageService {
    @GET("message")
    suspend fun getMessageData() : ResponseMessageData
}
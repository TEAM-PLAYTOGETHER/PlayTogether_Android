package com.playtogether_android.data.datasource.message

import com.playtogether_android.data.model.response.message.ResponseMessageData

interface MessageDataSource {
    suspend fun getMessageData() : ResponseMessageData
}
package com.playtogether_android.data.model.response.message

data class ResponseMessageSend(
    val data: Data,
    val message: String,
    val status: Int,
    val success: Boolean
){
    data class Data(
        val roomId: String
    )
}
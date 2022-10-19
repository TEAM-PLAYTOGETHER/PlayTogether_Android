package com.playtogether_android.app.presentation.ui.message.socketData.response

data class ResConnection(
    val data: String,
    val message: String,
    val status: Int,
    val success: Boolean
)
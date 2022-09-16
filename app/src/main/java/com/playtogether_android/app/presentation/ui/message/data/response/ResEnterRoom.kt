package com.playtogether_android.app.presentation.ui.message.data.response

data class ResEnterRoom(
    val data: Data,
    val message: String,
    val status: Int,
    val success: Boolean
) {
    data class Data(
        val rid: String,
        val uid: String
    )
}
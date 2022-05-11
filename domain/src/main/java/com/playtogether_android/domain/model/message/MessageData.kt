package com.playtogether_android.domain.model.message

data class MessageData(
    val success: Boolean,
    val data: List<Data>
) {
}
data class Data(
    val roomId: Int,
    val name: String,
    val read: Boolean,
    val time: String,
    val content: String
)
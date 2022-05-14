package com.playtogether_android.domain.model.message

data class MessageData(

    val audience: String,
    val audienceId: Int,
    val content: String,
    var createdAt: String,
    val read: Boolean,
    val roomId: Int,
    val send: Boolean

)

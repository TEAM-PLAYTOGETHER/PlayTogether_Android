package com.playtogether_android.domain.model.message

data class MessageListData(
    val roomId : Int,
    val name : String,
    val read : Boolean,
    val time : String,
    val content : String
)
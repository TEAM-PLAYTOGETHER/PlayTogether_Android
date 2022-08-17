package com.playtogether_android.domain.model.message

data class ChatData(
    val messageId: Int?,
    val content:String,
    var time:String,
    val messageType:Boolean,
    var timeVisible:Boolean=true
){
    companion object{
        const val TYPE_MY_MESSAGE = 0
        const val TYPE_FRIEND_MESSAGE = 1
    }
    private val viewType = if(messageType) TYPE_MY_MESSAGE else TYPE_FRIEND_MESSAGE

    fun getViewType() : Int {
        return viewType
    }
}

package com.playtogether_android.data.model.response.message

data class ResponseMessageData(
    val status : Int,
    val success : Boolean,
    val message : String,
    val data : List<Data>
){
    data class Data(
        val roomId : Int,
        val audience : String,
        val audienceId : Int,
        val send : Boolean,
        val read : Boolean,
        val createdAt : String,
        val content : String
    )
}

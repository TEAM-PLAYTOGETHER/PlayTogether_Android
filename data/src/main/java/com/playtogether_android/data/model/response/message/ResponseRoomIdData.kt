package com.playtogether_android.data.model.response.message

data class ResponseRoomIdData(
    val status : Int,
    val success : Boolean,
    val message : String,
    val data : RoomData
){
    data class RoomData(
        val roomId : String
    )
}

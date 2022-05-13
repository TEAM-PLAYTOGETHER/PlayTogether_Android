package com.playtogether_android.data.model.request.thunder

data class RequestThunderCreate(
    val title : String,
    val category : String,
    val date : String,
    val time : String,
    val place : String,
    val peoplCnt : Int,
    val description : String
)

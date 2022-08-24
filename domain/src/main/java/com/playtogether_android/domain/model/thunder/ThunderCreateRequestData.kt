package com.playtogether_android.domain.model.thunder

import com.google.gson.annotations.SerializedName
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

data class ThunderCreateRequestData(
    val title: String,
    val category: String,
    val date: String,
    @SerializedName("people_cnt")
    val peopleCnt: Int,
    val description: String,
    val time: String,
    val place: String,
//    val image: List<String>
) {
    fun toRequestBody(): HashMap<String, RequestBody> {
        return hashMapOf(
            "title" to title.toRequestBody("text/plain".toMediaTypeOrNull()),
            "category" to category.toRequestBody("text/plain".toMediaTypeOrNull()),
            "date" to date.toRequestBody("text/plain".toMediaTypeOrNull()),
            "peopleCnt" to peopleCnt.toString().toRequestBody("text/plain".toMediaTypeOrNull()),
            "description" to description.toRequestBody("text/plain".toMediaTypeOrNull()),
            "time" to time.toRequestBody("text/plain".toMediaTypeOrNull()),
            "place" to place.toRequestBody("text/plain".toMediaTypeOrNull())
        )
    }
}
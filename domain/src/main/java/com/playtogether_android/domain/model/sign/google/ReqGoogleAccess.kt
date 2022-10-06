package com.playtogether_android.domain.model.sign.google

import com.google.gson.annotations.SerializedName

data class ReqGoogleAccess(
    @SerializedName("grant_type")
     val grant_type: String,
    @SerializedName("client_id")
     val client_id: String,
    @SerializedName("client_secret")
     val client_secret: String,
    @SerializedName("code")
     val code: String
)

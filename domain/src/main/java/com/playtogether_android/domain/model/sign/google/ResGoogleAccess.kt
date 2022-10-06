package com.playtogether_android.domain.model.sign.google

import com.google.gson.annotations.SerializedName

data class ResGoogleAccess(
    @SerializedName("access_token") var access_token: String,
)

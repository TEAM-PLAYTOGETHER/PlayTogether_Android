package com.playtogether_android.data.model.response.sign

data class ResLoginGoogle(
    var access_token: String = "",
    var expires_in: Int = 0,
    var scope: String = "",
    var token_type: String = "",
    var id_token: String = "",
)

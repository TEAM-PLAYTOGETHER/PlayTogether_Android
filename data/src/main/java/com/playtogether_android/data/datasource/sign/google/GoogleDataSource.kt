package com.playtogether_android.data.datasource.sign.google

import com.playtogether_android.data.model.request.google.LoginGoogleRequestModel
import com.playtogether_android.data.model.request.google.LoginGoogleResponseModel

interface GoogleDataSource {
    suspend fun googleGetToken(request: LoginGoogleRequestModel): LoginGoogleResponseModel
}
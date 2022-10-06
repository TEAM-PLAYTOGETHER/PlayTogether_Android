package com.playtogether_android.data.datasource.sign.google

import com.playtogether_android.data.api.google_sign.GoogleService
import com.playtogether_android.data.model.request.google.LoginGoogleRequestModel
import com.playtogether_android.data.model.request.google.LoginGoogleResponseModel

class GoogleDataSourceImpl(private val service: GoogleService) : GoogleDataSource {
    override suspend fun googleGetToken(request: LoginGoogleRequestModel): LoginGoogleResponseModel {
        return service.getAccessToken(request)
    }
}
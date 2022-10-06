package com.playtogether_android.data.mapper.sign.google

import com.playtogether_android.data.model.request.google.LoginGoogleRequestModel
import com.playtogether_android.data.model.request.google.LoginGoogleResponseModel
import com.playtogether_android.domain.model.sign.google.ReqGoogleAccess
import com.playtogether_android.domain.model.sign.google.ResGoogleAccess

object GoogleMapper {
    fun mapperToReqAccessToken(request: ReqGoogleAccess): LoginGoogleRequestModel {
        return LoginGoogleRequestModel(
            grant_type = request.grant_type,
            client_id = request.client_id,
            client_secret = request.client_secret,
            code = request.code
        )
    }

    fun mapperToResAccessToken(response: LoginGoogleResponseModel): ResGoogleAccess {
        return ResGoogleAccess(response.access_token)
    }
}
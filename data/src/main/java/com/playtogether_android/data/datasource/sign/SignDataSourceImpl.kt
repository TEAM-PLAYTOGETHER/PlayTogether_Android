package com.playtogether_android.data.datasource.sign

import com.playtogether_android.data.api.sign.SignService
import com.playtogether_android.data.model.request.sign.RequestSignIn
import com.playtogether_android.data.model.request.sign.RequestSocialLogin
import com.playtogether_android.data.model.response.sign.ResSocialLogin
import com.playtogether_android.data.model.response.sign.ResponseSignIn

class SignDataSourceImpl(private val service: SignService) : SignDataSource {
    //로그인
    override suspend fun postSignIn(requestSignIn: RequestSignIn): ResponseSignIn {
        return service.postSignIn(requestSignIn)
    }

    override suspend fun postSocialLogin(
        platform: String,
        requestSocialLogin: RequestSocialLogin
    ): ResSocialLogin {
        return service.postSocialLogin(platform, requestSocialLogin)
    }
}
package com.playtogether_android.data.datasource.sign

import com.playtogether_android.data.model.request.sign.RequestSignIn
import com.playtogether_android.data.model.request.sign.RequestSocialLogin
import com.playtogether_android.data.model.response.sign.ResSocialLogin
import com.playtogether_android.data.model.response.sign.ResponseSignIn

interface SignDataSource {

    //로그인
    suspend fun postSignIn(requestSignIn: RequestSignIn): ResponseSignIn

    //소셜 로그인
    suspend fun postSocialLogin(
        platform: String,
        requestSocialLogin: RequestSocialLogin
    ): ResSocialLogin
}
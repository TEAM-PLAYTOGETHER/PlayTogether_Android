package com.playtogether_android.data.datasource.sign

import com.playtogether_android.data.api.sign.SignService
import com.playtogether_android.data.model.request.sign.RequestSignId
import com.playtogether_android.data.model.request.sign.RequestSignIn
import com.playtogether_android.data.model.request.sign.RequestSignUp
import com.playtogether_android.data.model.request.sign.RequestSocialLogin
import com.playtogether_android.data.model.response.sign.ResSocialLogin
import com.playtogether_android.data.model.response.sign.ResponseSignId
import com.playtogether_android.data.model.response.sign.ResponseSignIn
import com.playtogether_android.data.model.response.sign.ResponseSignUp

class SignDataSourceImpl(private val service: SignService) : SignDataSource {
    //아이디 중복 확인
    override suspend fun postSignId(requestSignId: RequestSignId): ResponseSignId {
        return service.postSignId(requestSignId)
    }

    //회원가입
    override suspend fun postSignUp(requestSignUp: RequestSignUp): ResponseSignUp {
        return service.postSignUp(requestSignUp)
    }

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
package com.playtogether_android.data.datasource.sign

import com.playtogether_android.data.model.request.sign.RequestSignId
import com.playtogether_android.data.model.request.sign.RequestSignIn
import com.playtogether_android.data.model.request.sign.RequestSignUp
import com.playtogether_android.data.model.request.sign.RequestSocialLogin
import com.playtogether_android.data.model.response.sign.ResSocialLogin
import com.playtogether_android.data.model.response.sign.ResponseSignId
import com.playtogether_android.data.model.response.sign.ResponseSignIn
import com.playtogether_android.data.model.response.sign.ResponseSignUp

interface SignDataSource {

    //아이디 중복확인
    suspend fun postSignId(requestSignId: RequestSignId): ResponseSignId

    //회원가입
    suspend fun postSignUp(requestSignUp: RequestSignUp): ResponseSignUp

    //로그인
    suspend fun postSignIn(requestSignIn: RequestSignIn): ResponseSignIn

    //소셜 로그인
    suspend fun postSocialLogin(
        platform: String,
        requestSocialLogin: RequestSocialLogin
    ): ResSocialLogin
}
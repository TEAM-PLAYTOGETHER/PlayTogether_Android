package com.playtogether_android.data.api.sign

import com.playtogether_android.data.model.request.sign.RequestSignIn
import com.playtogether_android.data.model.request.sign.RequestSocialLogin
import com.playtogether_android.data.model.response.sign.ResSocialLogin
import com.playtogether_android.data.model.response.sign.ResponseSignIn
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface SignService {
    //로그인
    @POST("auth/login")
    suspend fun postSignIn(
        @Body requestSignIn: RequestSignIn
    ): ResponseSignIn

    @POST("auth/{platform}-login")
    suspend fun postSocialLogin(
        @Path("platform") platform: String,
        @Body requestSocialLogin: RequestSocialLogin
    ): ResSocialLogin
}
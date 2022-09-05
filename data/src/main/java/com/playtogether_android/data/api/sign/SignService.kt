package com.playtogether_android.data.api.sign

import com.playtogether_android.data.model.request.sign.RequestSignIn
import com.playtogether_android.data.model.request.sign.RequestSignup
import com.playtogether_android.data.model.request.sign.RequestSocialLogin
import com.playtogether_android.data.model.response.sign.ResSocialLogin
import com.playtogether_android.data.model.response.sign.ResTokenIssuance
import com.playtogether_android.data.model.response.sign.ResponseSignIn
import com.playtogether_android.data.singleton.PlayTogetherRepository
import retrofit2.http.*

interface SignService {
    //로그인
    @POST("auth/login")
    suspend fun postSignIn(
        @Body requestSignIn: RequestSignIn
    ): ResponseSignIn

    //kakao login
    @POST("auth/kakao-login")
    suspend fun postKakaoLogin(
        @Header("accessToken") accessToken: String = PlayTogetherRepository.kakaoAccessToken,
        @Header("fcmToken") fcmToken: String = PlayTogetherRepository.fireBaseToken,
    ): ResSocialLogin

    //google login
    @POST("auth/google-login")
    suspend fun postGoogleLogin(
        @Header("accessToken") accessToken: String = PlayTogetherRepository.googleAccessToken,
        @Header("fcmToken") fcmToken: String = PlayTogetherRepository.fireBaseToken,
    ): ResSocialLogin

    // signup
    @PUT("user/signup")
    suspend fun putSignup(
        @Header("Authorization") authorization: String,
        @Body requestSignup: RequestSignup
    )

    // token re-issuance
    @GET("auth/refresh")
    suspend fun getTokenIssuance(
        @Header("Authorization") authorization: String,
        @Header("refresh") refresh: String
    ): ResTokenIssuance
}
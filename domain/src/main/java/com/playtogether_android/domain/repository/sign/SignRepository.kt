package com.playtogether_android.domain.repository.sign

import com.playtogether_android.domain.model.sign.*

interface SignRepository { //로그인
    suspend fun postSignIn(signInItem: SignInItem): SignInData

    suspend fun postKakaoLogin(): SocialLoginData

    suspend fun postGoogleLogin(): SocialLoginData

    suspend fun getTokenIssuance(accessToken: String, refreshToken: String): IssuanceItem

    suspend fun putSignup(authorization: String, body: UserInfo)

}
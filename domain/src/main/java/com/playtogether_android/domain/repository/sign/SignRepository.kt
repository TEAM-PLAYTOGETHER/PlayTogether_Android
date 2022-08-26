package com.playtogether_android.domain.repository.sign

import com.playtogether_android.domain.model.sign.*

interface SignRepository { //로그인
    suspend fun postSignIn(signInItem: SignInItem): SignInData

    suspend fun postSocialLogin(platform: String, socialLoginItem: SocialLoginItem): SocialLoginData
}
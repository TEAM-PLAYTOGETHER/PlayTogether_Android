package com.playtogether_android.data.mapper.sign

import com.playtogether_android.data.model.request.sign.RequestSignIn
import com.playtogether_android.data.model.request.sign.RequestSocialLogin
import com.playtogether_android.data.model.response.sign.ResSocialLogin
import com.playtogether_android.data.model.response.sign.ResponseSignIn
import com.playtogether_android.domain.model.sign.SignInData
import com.playtogether_android.domain.model.sign.SignInItem
import com.playtogether_android.domain.model.sign.SocialLoginData
import com.playtogether_android.domain.model.sign.SocialLoginItem

object SignMapper {
    //로그인 response
    fun mapperToSignInData(responseSignIn: ResponseSignIn): SignInData {
        return SignInData(
            success = responseSignIn.success,
            jwtToken = responseSignIn.data.jwtToken,
            userLoginId = responseSignIn.data.userLoginId,
            userName = responseSignIn.data.userName
        )
    }

    //로그인 request
    fun mapperToSignInItem(signInItem: SignInItem): RequestSignIn {
        return RequestSignIn(
            userLoginId = signInItem.userLoginId,
            password = signInItem.password
        )
    }

    fun mapperToSocialItem(socialLoginItem: SocialLoginItem): RequestSocialLogin {
        return RequestSocialLogin(
            accessToken = socialLoginItem.accessToken,
            fcmToken = socialLoginItem.fcmToken
        )
    }

    fun mapperToSocialData(resSocialLogin: ResSocialLogin): SocialLoginData {
        return SocialLoginData(
            accessToken = resSocialLogin.data.accessToken,
            refreshToken = resSocialLogin.data.refreshToken,
            userName = resSocialLogin.data.userName
        )
    }
}

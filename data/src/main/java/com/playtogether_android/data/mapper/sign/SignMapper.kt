package com.playtogether_android.data.mapper.sign

import com.playtogether_android.data.model.request.sign.RequestSignIn
import com.playtogether_android.data.model.request.sign.RequestSignup
import com.playtogether_android.data.model.request.sign.RequestSocialLogin
import com.playtogether_android.data.model.response.sign.ResSocialLogin
import com.playtogether_android.data.model.response.sign.ResTokenIssuance
import com.playtogether_android.data.model.response.sign.ResponseSignIn
import com.playtogether_android.domain.model.sign.*

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
            userName = resSocialLogin.data.userName,
            isSignup = resSocialLogin.data.isSignup
        )
    }

    fun mapperToIssuanceItem(data: ResTokenIssuance): IssuanceItem {
        return IssuanceItem(
            status = data.status,
            accessToken = data.data.accessToken,
            refreshToken = data.data.refreshToken
        )
    }

    fun mapperToUserInfo(data: UserInfo): RequestSignup {
        return RequestSignup(
            data.gender,
            data.birth
        )
    }
}

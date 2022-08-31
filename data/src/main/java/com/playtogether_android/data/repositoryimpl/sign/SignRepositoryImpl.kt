package com.playtogether_android.data.repositoryimpl.sign

import com.playtogether_android.data.datasource.sign.SignDataSource
import com.playtogether_android.data.mapper.sign.SignMapper
import com.playtogether_android.domain.model.sign.*
import com.playtogether_android.domain.repository.sign.SignRepository

class SignRepositoryImpl(private val signDataSource: SignDataSource) : SignRepository {
    //로그인
    override suspend fun postSignIn(signInItem: SignInItem): SignInData {
        return SignMapper.mapperToSignInData(
            signDataSource.postSignIn(
                SignMapper.mapperToSignInItem(signInItem)
            )
        )
    }

    override suspend fun postKakaoLogin(): SocialLoginData {
        return SignMapper.mapperToSocialData(signDataSource.postKakaoLogin())
    }

    override suspend fun postGoogleLogin(): SocialLoginData {
        return SignMapper.mapperToSocialData(signDataSource.postGoogleLogin())
    }

    override suspend fun getTokenIssuance(accessToken: String, refreshToken: String): IssuanceItem {
        return SignMapper.mapperToIssuanceItem(
            signDataSource.getTokenIssuance(
                accessToken,
                refreshToken
            )
        )
    }

    override suspend fun putSignup(authorization: String, body: UserInfo) {

    }
}
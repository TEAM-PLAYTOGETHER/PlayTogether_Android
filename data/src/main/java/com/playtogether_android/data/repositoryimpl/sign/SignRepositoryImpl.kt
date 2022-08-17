package com.playtogether_android.data.repositoryimpl.sign

import com.playtogether_android.data.datasource.sign.SignDataSource
import com.playtogether_android.data.mapper.sign.SignMapper
import com.playtogether_android.domain.model.sign.*
import com.playtogether_android.domain.repository.sign.SignRepository

class SignRepositoryImpl(private val signDataSource: SignDataSource) : SignRepository {

    //아이디 중복확인
    override suspend fun postSignId(idDuplicationCheckItem: IdDuplicationCheckItem): IdDuplicationCheckData {
        return SignMapper.mapperToIdDuplicationData(
            signDataSource.postSignId(
                SignMapper.mapperToIdDuplicationItem(idDuplicationCheckItem)
            )
        )
    }

    //회원가입
    override suspend fun postSignUp(signUpItem: SignUpItem): SignUpData {
        return SignMapper.mapperToSignupData(
            signDataSource.postSignUp(
                SignMapper.mapperToSignupItem(signUpItem)
            )
        )
    }

    //로그인
    override suspend fun postSignIn(signInItem: SignInItem): SignInData {
        return SignMapper.mapperToSignInData(
            signDataSource.postSignIn(
                SignMapper.mapperToSignInItem(signInItem)
            )
        )
    }

    override suspend fun postSocialLogin(
        platform: String,
        socialLoginItem: SocialLoginItem
    ): SocialLoginData {
        return SignMapper.mapperToSocialData(
            signDataSource.postSocialLogin(
                platform,
                SignMapper.mapperToSocialItem(socialLoginItem)
            )
        )
    }
}
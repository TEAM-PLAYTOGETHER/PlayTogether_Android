package com.playtogether_android.domain.repository.sign

import com.playtogether_android.domain.model.sign.*

interface SignRepository {
    //아이디 중복확인
    suspend fun postSignId(idDuplicationCheckItem: IdDuplicationCheckItem) : IdDuplicationCheckData

    //회원가입
    suspend fun postSignUp(signUpItem: SignUpItem) : SignUpData

    //로그인
    suspend fun postSignIn(signInItem: SignInItem) : SignInData
}
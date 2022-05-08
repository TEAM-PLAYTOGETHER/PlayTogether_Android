package com.playtogether_android.domain.repository.sign

import com.playtogether_android.domain.model.sign.IdDuplicationCheckData
import com.playtogether_android.domain.model.sign.IdDuplicationCheckItem
import com.playtogether_android.domain.model.sign.SignUpData
import com.playtogether_android.domain.model.sign.SignUpItem

interface SignRepository {
    //아이디 중복확인
    suspend fun postSignId(idDuplicationCheckItem: IdDuplicationCheckItem) : IdDuplicationCheckData

    //회원가입
    suspend fun postSignUp(signUpItem: SignUpItem) : SignUpData
}
package com.playtogether_android.domain.usecase.sign

import com.playtogether_android.domain.model.sign.IdDuplicationCheckData
import com.playtogether_android.domain.model.sign.IdDuplicationCheckItem
import com.playtogether_android.domain.model.sign.SignUpData
import com.playtogether_android.domain.model.sign.SignUpItem
import com.playtogether_android.domain.repository.sign.SignRepository

class PostSignIdUseCase(private val repository: SignRepository) {

    //아이디 중복확인
    suspend operator fun invoke(signIdDuplicationCheckItem: IdDuplicationCheckItem) : IdDuplicationCheckData{
        return repository.postSignId(signIdDuplicationCheckItem)
    }

    //회원가입
    suspend operator fun invoke(signUpItem: SignUpItem) : SignUpData {
        return repository.postSignUp(signUpItem)
    }

}
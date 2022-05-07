package com.playtogether_android.domain.usecase.sign

import com.playtogether_android.domain.model.sign.SignUpData
import com.playtogether_android.domain.model.sign.SignUpItem
import com.playtogether_android.domain.repository.sign.SignRepository

//회원가입
class PostSignUpUseCaes(private val repository: SignRepository) {
    suspend operator fun invoke(signUpItem: SignUpItem) : SignUpData {
        return repository.postSignUp(signUpItem)
    }
}
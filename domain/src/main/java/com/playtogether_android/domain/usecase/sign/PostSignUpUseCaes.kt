package com.playtogether_android.domain.usecase.sign

import com.playtogether_android.domain.model.sign.SignUpData
import com.playtogether_android.domain.model.sign.SignUpItem
import com.playtogether_android.domain.repository.sign.SignRepository
import javax.inject.Inject

//회원가입
class PostSignUpUseCaes @Inject constructor(private val repository: SignRepository) {
    suspend operator fun invoke(signUpItem: SignUpItem): SignUpData {
        return repository.postSignUp(signUpItem)
    }
}
package com.playtogether_android.domain.usecase.sign

import com.playtogether_android.domain.model.sign.SignInData
import com.playtogether_android.domain.model.sign.SignInItem
import com.playtogether_android.domain.model.sign.SignUpData
import com.playtogether_android.domain.model.sign.SignUpItem
import com.playtogether_android.domain.repository.sign.SignRepository
import javax.inject.Inject

//회원가입
class PostSignInUseCase @Inject constructor(private val repository: SignRepository) {
    suspend operator fun invoke(signInItem: SignInItem): SignInData {
        return repository.postSignIn(signInItem)
    }
}
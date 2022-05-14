package com.playtogether_android.domain.usecase.mypage

import com.playtogether_android.domain.model.mypage.UserCheckData
import com.playtogether_android.domain.repository.mypage.MyPageRepository

class GetUserCheckUseCase(private val repository: MyPageRepository) {
    suspend operator fun invoke(userLoginId: String) : UserCheckData{
        return repository.getUserCheck(userLoginId)
    }
}
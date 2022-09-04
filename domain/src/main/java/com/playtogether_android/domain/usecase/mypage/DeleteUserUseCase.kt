package com.playtogether_android.domain.usecase.mypage

import com.playtogether_android.domain.model.mypage.UserDeleteData
import com.playtogether_android.domain.repository.mypage.MyPageRepository
import javax.inject.Inject

class DeleteUserUseCase @Inject constructor(private val repository : MyPageRepository) {
    suspend operator fun invoke() : UserDeleteData {
        return repository.deleteUser()
    }
}
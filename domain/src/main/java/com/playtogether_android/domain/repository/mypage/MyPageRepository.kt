package com.playtogether_android.domain.repository.mypage

import com.playtogether_android.domain.model.mypage.UserCheckData
import com.playtogether_android.domain.model.mypage.UserDeleteData
import com.playtogether_android.domain.model.sign.*

interface MyPageRepository {
    //유저 조회
    suspend fun getUserCheck(userLoginId: String) : UserCheckData

    //유저 탈퇴
    suspend fun deleteUser() : UserDeleteData
}
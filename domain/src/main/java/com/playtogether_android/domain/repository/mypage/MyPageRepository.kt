package com.playtogether_android.domain.repository.mypage

import com.playtogether_android.domain.model.mypage.UserCheckData
import com.playtogether_android.domain.model.sign.*

interface MyPageRepository {
    //์ ์  ์กฐํ
    suspend fun getUserCheck(userLoginId: String) : UserCheckData
}
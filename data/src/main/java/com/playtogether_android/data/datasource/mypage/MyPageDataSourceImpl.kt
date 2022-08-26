package com.playtogether_android.data.datasource.mypage

import com.playtogether_android.data.api.mypage.MyPageService
import com.playtogether_android.data.model.response.mypage.ResponseUserCheck

class MyPageDataSourceImpl(private val service: MyPageService): MyPageDataSource {

    //유저 조회
    override suspend fun getUserCheck(userLoginId: String): ResponseUserCheck {
        return service.getUserCheck(userLoginId)
    }
}
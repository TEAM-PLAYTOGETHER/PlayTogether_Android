package com.playtogether_android.data.datasource.mypage

import com.playtogether_android.data.api.mypage.MyPageService
import com.playtogether_android.data.model.response.mypage.ResponseUserCheck
import com.playtogether_android.data.model.response.mypage.ResponseUserDelete

class MyPageDataSourceImpl(private val service: MyPageService): MyPageDataSource {

    //유저 조회
    override suspend fun getUserCheck(userLoginId: String): ResponseUserCheck {
        return service.getUserCheck(userLoginId)
    }

    override suspend fun deleteUser(): ResponseUserDelete {
        return service.deletUser()
    }
}
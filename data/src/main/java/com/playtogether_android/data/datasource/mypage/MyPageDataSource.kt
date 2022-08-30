package com.playtogether_android.data.datasource.mypage

import com.playtogether_android.data.model.response.mypage.ResponseUserCheck

interface MyPageDataSource {

    //유저 조회
    suspend fun getUserCheck(userLoginId: String):ResponseUserCheck

}
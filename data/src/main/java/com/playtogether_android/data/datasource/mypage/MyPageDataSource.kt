package com.playtogether_android.data.datasource.mypage

import com.playtogether_android.data.model.response.mypage.ResponseUserCheck
import com.playtogether_android.data.model.response.mypage.ResponseUserDelete

interface MyPageDataSource {

    //유저 조회
    suspend fun getUserCheck(userLoginId: String):ResponseUserCheck

    //유저 탈퇴
    suspend fun deleteUser() : ResponseUserDelete
}
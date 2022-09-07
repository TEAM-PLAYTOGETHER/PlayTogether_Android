package com.playtogether_android.data.datasource.userInfo

import com.playtogether_android.data.model.response.userInfo.ResMyInfoData

interface UserInfoDataSource {
    // 유저 본인 멀티프로필 상세 조회
    suspend fun getMyInfo(crewId: Int): ResMyInfoData
}
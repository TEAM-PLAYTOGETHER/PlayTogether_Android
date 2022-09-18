package com.playtogether_android.data.datasource.userInfo

import com.playtogether_android.data.model.response.userInfo.ResMyInfoData
import com.playtogether_android.data.model.response.userInfo.ResOtherInfoData

interface UserInfoDataSource {
    // 유저 본인 멀티프로필 상세 조회
    suspend fun getMyInfo(): ResMyInfoData

    // 동아리원 멀티프로필 상세 조회
    suspend fun getOtherInfo(crewId: Int,memberId: Int): ResOtherInfoData
}
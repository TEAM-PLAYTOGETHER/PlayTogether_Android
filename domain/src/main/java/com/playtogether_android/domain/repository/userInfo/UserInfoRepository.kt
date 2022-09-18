package com.playtogether_android.domain.repository.userInfo

import com.playtogether_android.domain.model.userInfo.MyInfoData
import com.playtogether_android.domain.model.userInfo.OtherInfoData

interface UserInfoRepository {

    // 유저 본인 멀티프로필 상세 조회
    suspend fun getMyInfo(): MyInfoData

    // 유저 멀티프로필 상세 조회
    suspend fun getOtherInfo(crewId: Int,memberId: Int): OtherInfoData
}
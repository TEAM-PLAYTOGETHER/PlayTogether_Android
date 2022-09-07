package com.playtogether_android.domain.repository.userInfo

import com.playtogether_android.domain.model.userInfo.MyInfoData

interface UserInfoRepository {

    // 유저 본인 멀티프로필 상세 조회
    suspend fun getMyInfo(crewId: Int): MyInfoData
}
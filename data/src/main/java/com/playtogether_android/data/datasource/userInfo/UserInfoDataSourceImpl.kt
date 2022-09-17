package com.playtogether_android.data.datasource.userInfo

import com.playtogether_android.data.api.userInfo.UserInfoService
import com.playtogether_android.data.model.response.userInfo.ResMyInfoData

class UserInfoDataSourceImpl(private val service: UserInfoService) : UserInfoDataSource {
    // 유저 본인 멀티프로필 상세 조회
//    override suspend fun getMyInfo(crewId: Int): ResMyInfoData {
//        return service.getMyInfo(crewId)
//    }

    override suspend fun getMyInfo(): ResMyInfoData {
        return service.getMyInfo()
    }
}
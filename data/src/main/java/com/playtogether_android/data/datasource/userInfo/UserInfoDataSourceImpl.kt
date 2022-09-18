package com.playtogether_android.data.datasource.userInfo

import com.playtogether_android.data.api.userInfo.UserInfoService
import com.playtogether_android.data.model.response.userInfo.ResBlockUserData
import com.playtogether_android.data.model.response.userInfo.ResMyInfoData
import com.playtogether_android.data.model.response.userInfo.ResOtherInfoData

class UserInfoDataSourceImpl(private val service: UserInfoService) : UserInfoDataSource {
    // 유저 본인 멀티프로필 상세 조회
    override suspend fun getMyInfo(): ResMyInfoData {
        return service.getMyInfo()
    }

    // 동아리원 멀티프로필 상세 조회
    override suspend fun getOtherInfo(crewId: Int, memberId: Int): ResOtherInfoData {
        return service.getOtherInfo(crewId, memberId)
    }

    // 유저 차단
    override suspend fun postBlockUser(memberId: Int): ResBlockUserData {
        return service.postBlockUser(memberId)
    }
}
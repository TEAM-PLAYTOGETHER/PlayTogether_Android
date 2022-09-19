package com.playtogether_android.data.datasource.userInfo

import com.playtogether_android.data.api.userInfo.UserInfoService
import com.playtogether_android.data.model.ResGenericData
import com.playtogether_android.data.model.response.userInfo.*

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

    // 동아리 탈퇴
    override suspend fun delCrew(): ResGenericData {
        return service.delCrew()
    }

    // 유저 차단 리스트 조회
    override suspend fun getBlockUserList(): ResBlockUserList {
        return service.getBlockUserList()
    }

    // 유저 차단 해제
    override suspend fun delUnblockUser(memberId: Int): ResUnblockUserData {
        return service.delUnblockUser(memberId)
    }
}
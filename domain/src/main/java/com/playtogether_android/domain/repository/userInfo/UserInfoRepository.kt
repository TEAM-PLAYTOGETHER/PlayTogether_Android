package com.playtogether_android.domain.repository.userInfo

import com.playtogether_android.domain.model.GenericData
import com.playtogether_android.domain.model.userInfo.BlockUserData
import com.playtogether_android.domain.model.userInfo.BlockUserList
import com.playtogether_android.domain.model.userInfo.MyInfoData
import com.playtogether_android.domain.model.userInfo.OtherInfoData

interface UserInfoRepository {

    // 유저 본인 멀티프로필 상세 조회
    suspend fun getMyInfo(): MyInfoData

    // 유저 멀티프로필 상세 조회
    suspend fun getOtherInfo(crewId: Int,memberId: Int): OtherInfoData

    // 유저 차단
    suspend fun postBlockUser(memberId: Int): BlockUserData

    // 동아리 탈퇴
    suspend fun delCrew(): GenericData

    // 유저 차단 리스트 조회
    suspend fun getBlockUserList(): BlockUserList
}
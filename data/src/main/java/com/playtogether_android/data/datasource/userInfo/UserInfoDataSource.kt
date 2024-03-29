package com.playtogether_android.data.datasource.userInfo

import com.playtogether_android.data.model.ResGenericData
import com.playtogether_android.data.model.response.userInfo.*
import okhttp3.MultipartBody

interface UserInfoDataSource {
    // 유저 본인 멀티프로필 상세 조회
    suspend fun getMyInfo(): ResMyInfoData

    // 동아리원 멀티프로필 상세 조회
    suspend fun getOtherInfo(crewId: Int,memberId: Int): ResOtherInfoData

    // 유저 차단
    suspend fun postBlockUser(memberId: Int): ResBlockUserData

    // 동아리 탈퇴
    suspend fun delCrew(): ResGenericData

    // 유저 차단 리스트 조회
    suspend fun getBlockUserList(): ResBlockUserList

    // 유저 차단 해제
    suspend fun delUnblockUser(memberId: Int): ResUnblockUserData

    // 유저 멀티프로필 이미지 추가
    suspend fun putProfileImage(
        crewId: Int,
        image: MultipartBody.Part?
    ): ResGenericData
}
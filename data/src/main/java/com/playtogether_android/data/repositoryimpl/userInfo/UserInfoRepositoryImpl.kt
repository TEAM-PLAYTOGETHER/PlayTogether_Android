package com.playtogether_android.data.repositoryimpl.userInfo

import com.playtogether_android.data.datasource.userInfo.UserInfoDataSource
import com.playtogether_android.data.mapper.userInfo.UserInfoMapper
import com.playtogether_android.domain.model.userInfo.MyInfoData
import com.playtogether_android.domain.model.userInfo.OtherInfoData
import com.playtogether_android.domain.repository.userInfo.UserInfoRepository

class UserInfoRepositoryImpl(private val userInfoDataSource: UserInfoDataSource) : UserInfoRepository {

    // 유저 본인 멀티프로필 상세 조회
    override suspend fun getMyInfo(): MyInfoData {
        return UserInfoMapper.mapperToMyInfoData(userInfoDataSource.getMyInfo().data)
    }

    // 유저 멀티프로필 상세 조회
    override suspend fun getOtherInfo(crewId: Int, memberId: Int): OtherInfoData {
        return UserInfoMapper.mapperToOtherInfoData(userInfoDataSource.getOtherInfo(crewId, memberId).data)
    }
}
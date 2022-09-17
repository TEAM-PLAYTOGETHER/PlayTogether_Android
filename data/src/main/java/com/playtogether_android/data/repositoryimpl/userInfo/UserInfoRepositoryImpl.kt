package com.playtogether_android.data.repositoryimpl.userInfo

import com.playtogether_android.data.datasource.userInfo.UserInfoDataSource
import com.playtogether_android.data.mapper.userInfo.UserInfoMapper
import com.playtogether_android.domain.model.userInfo.MyInfoData
import com.playtogether_android.domain.repository.userInfo.UserInfoRepository

class UserInfoRepositoryImpl(private val userInfoDataSource: UserInfoDataSource) : UserInfoRepository {

    // 유저 본인 멀티프로필 상세 조회
    override suspend fun getMyInfo(): MyInfoData {
        return UserInfoMapper.mapperToMyInfoData(userInfoDataSource.getMyInfo().data)
    }
}
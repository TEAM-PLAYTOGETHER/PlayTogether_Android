package com.playtogether_android.data.repositoryimpl.userInfo

import com.playtogether_android.data.datasource.userInfo.UserInfoDataSource
import com.playtogether_android.data.mapper.userInfo.UserInfoMapper
import com.playtogether_android.domain.model.userInfo.MyInfoData
import com.playtogether_android.domain.repository.userInfo.UserInfoRepository

class UserInfoRepositoryImpl(private val userInfoDataSource: UserInfoDataSource) : UserInfoRepository {
//    override suspend fun getMyInfo(crewId: Int): MyInfoData {
//        return UserInfoMapper.mapperToMyInfoData(userInfoDataSource.getMyInfo(crewId))
//    }

    override suspend fun getMyInfo(): MyInfoData {
        return UserInfoMapper.mapperToMyInfoData(userInfoDataSource.getMyInfo().data)
    }
}
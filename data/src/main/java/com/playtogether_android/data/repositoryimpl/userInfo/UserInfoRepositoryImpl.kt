package com.playtogether_android.data.repositoryimpl.userInfo

import com.playtogether_android.data.datasource.userInfo.UserInfoDataSource
import com.playtogether_android.data.mapper.userInfo.UserInfoMapper
import com.playtogether_android.domain.model.GenericData
import com.playtogether_android.domain.model.userInfo.BlockUserData
import com.playtogether_android.domain.model.userInfo.BlockUserList
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

    // 유저 차단
    override suspend fun postBlockUser(memberId: Int): BlockUserData {
        return UserInfoMapper.mapperToBlockUserData(userInfoDataSource.postBlockUser(memberId).data)
    }

    // 동아리 탈퇴
    override suspend fun delCrew(): GenericData {
        return with(userInfoDataSource.delCrew()) {
            GenericData(
                this.status,
                this.success,
                this.message
            )
        }
    }

    // 유저 차단 리스트 조회
    override suspend fun getBlockUserList(): BlockUserList {
        return UserInfoMapper.mapperToBlockUserList(userInfoDataSource.getBlockUserList())
    }
}
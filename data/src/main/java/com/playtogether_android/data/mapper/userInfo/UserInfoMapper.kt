package com.playtogether_android.data.mapper.userInfo

import com.playtogether_android.data.model.response.userInfo.ResBlockUserData
import com.playtogether_android.data.model.response.userInfo.ResBlockUserList
import com.playtogether_android.data.model.response.userInfo.ResMyInfoData
import com.playtogether_android.data.model.response.userInfo.ResOtherInfoData
import com.playtogether_android.domain.model.userInfo.BlockUserData
import com.playtogether_android.domain.model.userInfo.BlockUserList
import com.playtogether_android.domain.model.userInfo.MyInfoData
import com.playtogether_android.domain.model.userInfo.OtherInfoData

object UserInfoMapper {

    // 유저 본인 멀티프로필 상세 조회
    fun mapperToMyInfoData(resMyInfoData: ResMyInfoData.Data): MyInfoData {
        return MyInfoData(
            id = resMyInfoData.profile.id,
            isDeleted = resMyInfoData.profile.isDeleted,
            nickname = resMyInfoData.profile.nickname,
            description = resMyInfoData.profile.description,
            firstStation = resMyInfoData.profile.firstStation,
            secondStation = resMyInfoData.profile.secondStation,
            profileImage = resMyInfoData.profile.profileImage,
            gender = resMyInfoData.profile.gender,
            birth = resMyInfoData.profile.birth,
            crewName = resMyInfoData.crewName
        )
    }

    // 유저 멀티프로필 상세 조회
    fun mapperToOtherInfoData(resOtherInfoData: ResOtherInfoData.Data): OtherInfoData {
        return OtherInfoData(
            id = resOtherInfoData.profile.id,
            isDeleted = resOtherInfoData.profile.isDeleted,
            nickname = resOtherInfoData.profile.nickname,
            description = resOtherInfoData.profile.description,
            firstStation = resOtherInfoData.profile.firstStation,
            secondStation = resOtherInfoData.profile.secondStation,
            profileImage = resOtherInfoData.profile.profileImage,
            gender = resOtherInfoData.profile.gender,
            birth = resOtherInfoData.profile.birth,
            crewName = resOtherInfoData.crewName
        )
    }

    // 유저 차단
    fun mapperToBlockUserData(resBlockUserData: ResBlockUserData.Data): BlockUserData {
        return BlockUserData(
            id = resBlockUserData.id,
            userId = resBlockUserData.userId,
            blockUserId = resBlockUserData.blockUserId,
            createdAt = resBlockUserData.createdAt,
            updatedAt = resBlockUserData.updatedAt
        )
    }

    // 유저 차단 리스트 조회
    fun mapperToBlockUserList(resBlockUserList: ResBlockUserList): BlockUserList {
        return BlockUserList(
            data = resBlockUserList.data.map {
                BlockUserList.Block(it.id, it.userId, it.blockUserId, it.createdAt, it.updatedAt)
            }
        )
    }
}
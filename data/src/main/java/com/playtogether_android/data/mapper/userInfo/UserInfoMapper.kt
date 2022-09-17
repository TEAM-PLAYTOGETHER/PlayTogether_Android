package com.playtogether_android.data.mapper.userInfo

import com.playtogether_android.data.model.response.userInfo.ResMyInfoData
import com.playtogether_android.domain.model.userInfo.MyInfoData

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
            birth = resMyInfoData.profile.birth
        )
    }
}
package com.playtogether_android.data.mapper.userInfo

import com.playtogether_android.data.model.response.userInfo.ResMyInfoData
import com.playtogether_android.domain.model.userInfo.MyInfoData

object UserInfoMapper {

    // 유저 본인 멀티프로필 상세 조회
    fun mapperToMyInfoData(resMyInfoData: ResMyInfoData.Data): MyInfoData {
        return MyInfoData(
            resMyInfoData.profile.id,
            resMyInfoData.crewName,
            resMyInfoData.profile.isDeleted,
            resMyInfoData.profile.nickname,
            resMyInfoData.profile.description,
            resMyInfoData.profile.firstStation,
            resMyInfoData.profile.secondStation,
            resMyInfoData.profile.profileImage,
            resMyInfoData.profile.gender,
            resMyInfoData.profile.birth
        )
    }
}
package com.playtogether_android.data.mapper.userInfo

import com.playtogether_android.data.model.response.userInfo.ResMyInfoData
import com.playtogether_android.domain.model.userInfo.MyInfoData

object UserInfoMapper {

    // 유저 본인 멀티프로필 상세 조회
    fun mapperToMyInfoData(resMyInfoData: ResMyInfoData) : MyInfoData {
        return MyInfoData(
            id = resMyInfoData.data.id,
            isDeleted = resMyInfoData.data.isDeleted,
            nickname = resMyInfoData.data.nickname,
            description = resMyInfoData.data.description,
            firstStation = resMyInfoData.data.firstStation,
            secondStation = resMyInfoData.data.secondStation,
            profileImage = resMyInfoData.data.profileImage,
            gender = resMyInfoData.data.gender,
            birth = resMyInfoData.data.birth
        )
    }
}
package com.playtogether_android.data.mapper.mypage

import com.playtogether_android.data.model.response.mypage.ResponseUserCheck
import com.playtogether_android.domain.model.mypage.UserCheckData

object MyPageMapper {
    fun mapperToUserCheck(responseUserCheck: ResponseUserCheck) : UserCheckData {
        return UserCheckData(
            age = responseUserCheck.data.age,
            mbti = responseUserCheck.data.mbti,
            name = responseUserCheck.data.name,
            gender = responseUserCheck.data.gender,
            userLoginId = responseUserCheck.data.userLoginId
        )
    }
}
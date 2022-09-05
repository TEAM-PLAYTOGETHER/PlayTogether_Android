package com.playtogether_android.data.mapper.mypage

import com.playtogether_android.data.model.response.mypage.ResponseUserCheck
import com.playtogether_android.data.model.response.mypage.ResponseUserDelete
import com.playtogether_android.domain.model.mypage.UserCheckData
import com.playtogether_android.domain.model.mypage.UserDeleteData

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

    fun mapperToUserDelete(responseUserDelete: ResponseUserDelete) : UserDeleteData {
        return UserDeleteData(
            message = responseUserDelete.message,
            success = responseUserDelete.success
        )
    }
}
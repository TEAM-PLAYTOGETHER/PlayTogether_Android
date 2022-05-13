package com.playtogether_android.data.mapper.home

import com.playtogether_android.data.model.response.home.ResponseJoinThunder
import com.playtogether_android.data.model.response.mypage.ResponseUserCheck
import com.playtogether_android.domain.model.home.JoinThunderData
import com.playtogether_android.domain.model.mypage.UserCheckData

object HomeMapper {
    fun mapperToJoinThunder(responseJoinThunder: ResponseJoinThunder) : JoinThunderData {
        return JoinThunderData(
            success = responseJoinThunder.success
        )
    }
}
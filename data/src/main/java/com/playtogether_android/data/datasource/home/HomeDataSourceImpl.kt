package com.playtogether_android.data.datasource.home

import com.playtogether_android.data.api.home.HomeService
import com.playtogether_android.data.api.light.LightService
import com.playtogether_android.data.model.response.home.ResponseJoinThunder
import com.playtogether_android.data.model.response.home.ResponseThunderJoinEnd
import com.playtogether_android.data.model.response.light.ResponseLightJoinCancel
import com.playtogether_android.data.model.response.light.ResponseLightListCategory
import com.playtogether_android.data.model.response.mypage.ResponseUserCheck

class HomeDataSourceImpl(private val homeService: HomeService) : HomeDataSource {
    //유저 조회
    override suspend fun postJoinThunder(lightId: Int): ResponseJoinThunder {
        return homeService.JoinThunder(lightId)
    }

    override suspend fun getThunderJoinEnd(lightId: Int): ResponseThunderJoinEnd {
        return homeService.thunderThunderEnd(lightId)
    }
}
package com.playtogether_android.data.datasource.home

import com.playtogether_android.data.model.response.home.ResponseJoinThunder
import com.playtogether_android.data.model.response.light.ResponseLightJoinCancel
import com.playtogether_android.data.model.response.light.ResponseLightListCategory
import com.playtogether_android.data.model.response.mypage.ResponseUserCheck

interface HomeDataSource {
    suspend fun postJoinThunder(lightId: Int): ResponseJoinThunder
}
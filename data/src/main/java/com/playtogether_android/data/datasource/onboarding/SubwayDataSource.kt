package com.playtogether_android.data.datasource.onboarding

import com.playtogether_android.data.model.response.onboarding.ResponseSubwayList

interface SubwayDataSource {

    //지하철 리스트 조회
    suspend fun getSubwayList() : ResponseSubwayList

}
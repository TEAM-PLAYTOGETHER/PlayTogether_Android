package com.playtogether_android.data.datasource.onboarding

import com.playtogether_android.data.api.onboarding.SubwayInfoService
import com.playtogether_android.data.model.response.onboarding.ResponseSubwayList

class SubwayDataSourceImpl (private val service: SubwayInfoService): SubwayDataSource {

    override suspend fun getSubwayList(): ResponseSubwayList {
        return service.getSubwayInfo()
    }

}

package com.playtogether_android.data.repositoryimpl.onboarding

import com.playtogether_android.data.datasource.onboarding.SubwayDataSource
import com.playtogether_android.data.mapper.onboarding.OnBoardingMapper
import com.playtogether_android.domain.model.onboarding.SubwayListData
import com.playtogether_android.domain.repository.onboarding.SubwayRepository

class SubwayRepositoryImpl(
    private val subwayInfoService: SubwayDataSource
) :
    SubwayRepository {

    override suspend fun getSubwayList(): SubwayListData {
        return OnBoardingMapper.mapperToSubwayList(
            subwayInfoService.getSubwayList()
        )
    }
}
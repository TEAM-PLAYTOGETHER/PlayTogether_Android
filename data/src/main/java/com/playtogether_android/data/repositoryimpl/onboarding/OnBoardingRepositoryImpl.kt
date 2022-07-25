package com.playtogether_android.data.repositoryimpl.onboarding

import com.playtogether_android.data.datasource.onboarding.OnBoardingDataSource
import com.playtogether_android.data.mapper.onboarding.OnBoardingMapper
import com.playtogether_android.domain.model.onboarding.MakeCrewData
import com.playtogether_android.domain.model.onboarding.MakeCrewItem
import com.playtogether_android.domain.model.onboarding.RegisterCrewData
import com.playtogether_android.domain.model.onboarding.RegisterCrewItem
import com.playtogether_android.domain.repository.onboarding.OnBoardingRepository

class OnBoardingRepositoryImpl (private val onBoardingDataSource: OnBoardingDataSource) : OnBoardingRepository {

    override suspend fun postRegisterCrew(registerCrewItem: RegisterCrewItem): RegisterCrewData {
        return OnBoardingMapper.mapperToRegisterCrewData(onBoardingDataSource.postRegisterCrew(
            OnBoardingMapper.mapperToRegisterCrewItem(registerCrewItem)
        ))
    }

    override suspend fun postMakeCrew(makeCrewItem: MakeCrewItem): MakeCrewData {
        return OnBoardingMapper.mapperToMakeCrewData(onBoardingDataSource.postMakeCrew(
            OnBoardingMapper.mapperToMakeCrewItem(makeCrewItem)
        ))
    }
}
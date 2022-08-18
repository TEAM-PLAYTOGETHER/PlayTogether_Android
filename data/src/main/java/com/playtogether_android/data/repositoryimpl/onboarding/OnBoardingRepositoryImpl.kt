package com.playtogether_android.data.repositoryimpl.onboarding

import com.playtogether_android.data.datasource.onboarding.OnBoardingDataSource
import com.playtogether_android.data.mapper.onboarding.OnBoardingMapper
import com.playtogether_android.domain.model.onboarding.*
import com.playtogether_android.domain.repository.onboarding.OnBoardingRepository

class OnBoardingRepositoryImpl(
    private val onBoardingDataSource: OnBoardingDataSource
) :
    OnBoardingRepository {

    override suspend fun postRegisterCrew(registerCrewItem: RegisterCrewItem): RegisterCrewData {
        return OnBoardingMapper.mapperToRegisterCrewData(
            onBoardingDataSource.postRegisterCrew(
                OnBoardingMapper.mapperToRegisterCrewItem(registerCrewItem)
            )
        )
    }


    override suspend fun postMakeCrew(makeCrewItem: MakeCrewItem): MakeCrewData {
        return OnBoardingMapper.mapperToMakeCrewData(
            onBoardingDataSource.postMakeCrew(
                OnBoardingMapper.mapperToMakeCrewItem(makeCrewItem)

            )
        )
    }

    override suspend fun getCrewList(): CrewListData {
        return OnBoardingMapper.mapperToGetList(
            onBoardingDataSource.getListCrew()
        )
    }

    override suspend fun getNickNameDuplication(
        crewId: Int,
        nickname: String
    ): NickNameDuplicationData {
        return OnBoardingMapper.mapperToNicknameDuplication(onBoardingDataSource.getNickNameDuplication(crewId, nickname))
    }

    override suspend fun putAddProfile(
        addProfileItem: AddProfileItem,
        crewId: Int
    ): AddProfileData {
        return OnBoardingMapper.mapperToAddProfileData(
            onBoardingDataSource.putAddProfile(
                OnBoardingMapper.mapperToAddProfileItem(addProfileItem), crewId
            )
        )
    }

}
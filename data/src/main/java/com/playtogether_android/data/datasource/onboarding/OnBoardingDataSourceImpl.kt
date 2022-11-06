package com.playtogether_android.data.datasource.onboarding

import com.playtogether_android.data.api.onboarding.OnboardingService
import com.playtogether_android.data.model.request.onboarding.RequestAddProfile
import com.playtogether_android.data.model.request.onboarding.RequestMakeCrew
import com.playtogether_android.data.model.request.onboarding.RequestRegisterCrew
import com.playtogether_android.data.model.response.onboarding.*

class OnBoardingDataSourceImpl (private val service: OnboardingService): OnBoardingDataSource {
    override suspend fun postRegisterCrew(requestRegisterCrew: RequestRegisterCrew): ResponseRegisterCrew {
        return service.postRegisterCrew(requestRegisterCrew)
    }

    override suspend fun postMakeCrew(requestMakeCrew: RequestMakeCrew): ResponseMakeCrew {
        return service.postMakeCrew(requestMakeCrew)

    }

    override suspend fun getListCrew(): ResponseGetList {
        return service.getCrewList()
    }

    override suspend fun getNickNameDuplication(
        crewId: Int,
        nickname: String
    ): ResponseGetNickNameDuplication {
        return service.getNickNameDuplication(crewId, nickname)
    }

    override suspend fun putAddProfile(
        requestAddProfile: RequestAddProfile,
        crewId: Int
    ): ResponseAddProfile {
        return service.putAddProfile(requestAddProfile, crewId)
    }

    override suspend fun getCheckExist(crewCode: String): ResponseCheckExist {
        return service.getCheckExist(crewCode)
    }

}

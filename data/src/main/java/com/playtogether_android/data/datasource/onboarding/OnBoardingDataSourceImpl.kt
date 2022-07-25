package com.playtogether_android.data.datasource.onboarding

import com.playtogether_android.data.api.onboarding.OnboardingService
import com.playtogether_android.data.model.request.onboarding.RequestMakeCrew
import com.playtogether_android.data.model.request.onboarding.RequestRegisterCrew
import com.playtogether_android.data.model.response.onboarding.ResponseMakeCrew
import com.playtogether_android.data.model.response.onboarding.ResponseRegisterCrew

class OnBoardingDataSourceImpl (private val service: OnboardingService): OnBoardingDataSource {
    override suspend fun postRegisterCrew(requestRegisterCrew: RequestRegisterCrew): ResponseRegisterCrew {
        return service.postRegisterCrew(requestRegisterCrew)
    }

    override suspend fun postMakeCrew(requestMakeCrew: RequestMakeCrew): ResponseMakeCrew {
        return service.postMakeCrew(requestMakeCrew)

    }

}

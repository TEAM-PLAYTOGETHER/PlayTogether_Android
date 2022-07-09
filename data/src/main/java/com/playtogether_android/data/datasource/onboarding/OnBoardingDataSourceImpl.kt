package com.playtogether_android.data.datasource.onboarding

import com.playtogether_android.data.api.onboarding.OnboardingService
import com.playtogether_android.data.api.sign.SignService
import com.playtogether_android.data.datasource.sign.SignDataSource
import com.playtogether_android.data.model.request.onboarding.RequestCrew
import com.playtogether_android.data.model.request.onboarding.RequestRegisterCrew
import com.playtogether_android.data.model.request.sign.RequestSignId
import com.playtogether_android.data.model.response.onboarding.ResponseCrew
import com.playtogether_android.data.model.response.onboarding.ResponseRegisterCrew
import com.playtogether_android.data.model.response.sign.ResponseSignId

class OnBoardingDataSourceImpl (private val service: OnboardingService): OnBoardingDataSource {
    override suspend fun postRegisterCrew(requestRegisterCrew: RequestRegisterCrew): ResponseRegisterCrew {
        return service.postRegisterCrew(requestRegisterCrew)
    }

    override suspend fun postCrew(requestCrew: RequestCrew): ResponseCrew {
        return service.postCrew(requestCrew)
    }

}

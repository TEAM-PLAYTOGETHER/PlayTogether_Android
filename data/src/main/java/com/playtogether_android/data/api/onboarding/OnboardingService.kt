package com.playtogether_android.data.api.onboarding

import com.playtogether_android.data.model.request.onboarding.RequestRegisterCrew
import com.playtogether_android.data.model.request.sign.RequestSignId
import com.playtogether_android.data.model.response.onboarding.ResponseRegisterCrew
import com.playtogether_android.data.model.response.sign.ResponseSignId
import retrofit2.http.Body
import retrofit2.http.POST

interface OnboardingService {
    //아이디 중복확인
    @POST("crew/register")
    suspend fun postRegisterCrew(
        @Body requestRegisterCrew: RequestRegisterCrew
    ): ResponseRegisterCrew
}
package com.playtogether_android.data.datasource.onboarding

import com.playtogether_android.data.model.request.onboarding.RequestMakeCrew
import com.playtogether_android.data.model.request.onboarding.RequestRegisterCrew
import com.playtogether_android.data.model.response.onboarding.ResponseMakeCrew
import com.playtogether_android.data.model.response.onboarding.ResponseRegisterCrew

interface OnBoardingDataSource {

    //동아리 가입
    suspend fun postRegisterCrew(requestRegisterCrew: RequestRegisterCrew) : ResponseRegisterCrew

    //동아리 생성
    suspend fun postMakeCrew(requestMakeCrew: RequestMakeCrew) : ResponseMakeCrew

}
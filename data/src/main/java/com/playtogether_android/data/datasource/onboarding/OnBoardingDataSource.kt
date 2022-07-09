package com.playtogether_android.data.datasource.onboarding

import com.playtogether_android.data.model.request.onboarding.RequestCrew
import com.playtogether_android.data.model.request.onboarding.RequestRegisterCrew
import com.playtogether_android.data.model.request.sign.RequestSignId
import com.playtogether_android.data.model.response.onboarding.ResponseCrew
import com.playtogether_android.data.model.response.onboarding.ResponseRegisterCrew
import com.playtogether_android.data.model.response.sign.ResponseSignId

interface OnBoardingDataSource {

    //동아리 가입
    suspend fun postRegisterCrew(requestRegisterCrew: RequestRegisterCrew) : ResponseRegisterCrew

    //동아리 생성
    suspend fun postCrew(requestCrew: RequestCrew) : ResponseCrew
}
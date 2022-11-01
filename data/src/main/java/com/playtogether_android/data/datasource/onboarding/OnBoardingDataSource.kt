package com.playtogether_android.data.datasource.onboarding

import com.playtogether_android.data.model.request.onboarding.RequestAddProfile
import com.playtogether_android.data.model.request.onboarding.RequestMakeCrew
import com.playtogether_android.data.model.request.onboarding.RequestRegisterCrew
import com.playtogether_android.data.model.response.onboarding.*

interface OnBoardingDataSource {

    //동아리 가입
    suspend fun postRegisterCrew(requestRegisterCrew: RequestRegisterCrew): ResponseRegisterCrew

    //동아리 생성
    suspend fun postMakeCrew(requestMakeCrew: RequestMakeCrew): ResponseMakeCrew

    //동아리 리스트 조회
    suspend fun getListCrew(): ResponseGetList

    suspend fun getNickNameDuplication(
        crewId: Int,
        nickname: String
    ): ResponseGetNickNameDuplication

    suspend fun putAddProfile(requestAddProfile: RequestAddProfile, crewId: Int): ResponseAddProfile

    suspend fun getCheckExist(crewCode: String): ResponseCheckExist
}
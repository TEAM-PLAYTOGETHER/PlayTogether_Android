package com.playtogether_android.data.api.onboarding


import com.playtogether_android.data.model.request.onboarding.RequestMakeCrew
import com.playtogether_android.data.model.request.onboarding.RequestRegisterCrew
import com.playtogether_android.data.model.response.onboarding.ResponseGetList
import com.playtogether_android.data.model.response.onboarding.ResponseMakeCrew
import com.playtogether_android.data.model.response.onboarding.ResponseRegisterCrew
import com.playtogether_android.data.model.response.onboarding.ResponseSubwayList
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface OnboardingService {
    //아이디 중복확인
    @POST("crew/register")
    suspend fun postRegisterCrew(
        @Body requestRegisterCrew: RequestRegisterCrew
    ): ResponseRegisterCrew

    //동아리 생성
    @POST("crew")
    suspend fun postMakeCrew(
        @Body requestMakeCrew: RequestMakeCrew
    ) : ResponseMakeCrew

    //가입한 동아리 리스트
    @GET("crew/list")
    suspend fun getCrewList() : ResponseGetList



}
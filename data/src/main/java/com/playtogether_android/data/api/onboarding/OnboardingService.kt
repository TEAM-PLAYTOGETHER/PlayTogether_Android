package com.playtogether_android.data.api.onboarding


import com.playtogether_android.data.model.request.onboarding.RequestAddProfile
import com.playtogether_android.data.model.request.onboarding.RequestMakeCrew
import com.playtogether_android.data.model.request.onboarding.RequestRegisterCrew
import com.playtogether_android.data.model.response.onboarding.*
import com.playtogether_android.data.singleton.PlayTogetherRepository
import retrofit2.http.*

interface OnboardingService {
    @Headers("Content-Type:application/json")
    @POST("crew/register")
    suspend fun postRegisterCrew(
        @Body requestRegisterCrew: RequestRegisterCrew
    ): ResponseRegisterCrew

    //동아리 생성
   // @Headers("Content-Type:application/json", "Authorization:eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjE3MCIsImVtYWlsIjoibGhiODEwNkBuYXRlLmNvbSIsImlhdCI6MTY2MjA0NDU4OSwiZXhwIjoxNjYyMDQ4MTg5LCJpc3MiOiJwbGF5dG9nZXRoZXIifQ.YqE8_0nL7xThTajEercBl5HAWDP2nvoWhG4yXQ-BbQ0")
    @POST("crew")
    suspend fun postMakeCrew(
        @Body requestMakeCrew: RequestMakeCrew
    ) : ResponseMakeCrew

    //가입한 동아리 리스트
    @GET("crew/list")
    suspend fun getCrewList() : ResponseGetList

    //TODO 여기 부분인데 뭔지 모르겠어요
    //유저 닉네임 중복확인
    @GET("user/crew/{crewId}")
    suspend fun getNickNameDuplication(
        @Path("crewId") crewId : Int,
        @Query("nickname") nickname : String
    ) : ResponseGetNickNameDuplication

    //멀티 프로필 등록
   // @Headers("Content-Type:application/json", "Authorization:eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjQ0IiwidXNlckxvZ2luSWQiOiJicmlhbmRyIiwiaWF0IjoxNjU5MjM2Mjg3LCJleHAiOjE2NjE4MjgyODcsImlzcyI6InBsYXl0b2dldGhlciJ9.N_qdonODmdOPpTdre-zY1RmZXnTFHeN72OTc3zYUTcY")
    @PUT("user/{crewId}")
    suspend fun putAddProfile(
        @Body requestAddProfile: RequestAddProfile,
        @Path("crewId") crewId : Int
    ) : ResponseAddProfile


}
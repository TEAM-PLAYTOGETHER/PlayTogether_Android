package com.playtogether_android.data.api.onboarding


import com.playtogether_android.data.model.request.onboarding.RequestAddProfile
import com.playtogether_android.data.model.request.onboarding.RequestMakeCrew
import com.playtogether_android.data.model.request.onboarding.RequestRegisterCrew
import com.playtogether_android.data.model.response.onboarding.*
import retrofit2.http.*

interface OnboardingService {
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

    //유저 닉네임 중복확인
    @GET("user/crew/{crewId}/nickname")
    suspend fun getNickNameDuplication(
        @Path("crewId") crewId : Int,
        @Query("nickname") nickname : String
    ) : ResponseGetNickNameDuplication

    //멀티 프로필 등록
    @PUT("user/{crewId}")
    suspend fun putAddProfile(
        @Body requestAddProfile: RequestAddProfile,
        @Path("crewId") crewId : Int
    ) : ResponseAddProfile

    //동아리 가입가능 확인
    @GET("crew/checkExist/{crewCode}")
    suspend fun getCheckExist(
        @Path("crewCode") crewCode : String
    ) : ResponseCheckExist

}
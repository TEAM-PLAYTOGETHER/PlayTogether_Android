package com.playtogether_android.data.api.onboarding


import com.playtogether_android.data.model.request.onboarding.RequestAddProfile
import com.playtogether_android.data.model.request.onboarding.RequestMakeCrew
import com.playtogether_android.data.model.request.onboarding.RequestRegisterCrew
import com.playtogether_android.data.model.response.onboarding.*
import retrofit2.http.*

interface OnboardingService {
    @Headers("Content-Type:application/json", "Authorization:eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjE2OSIsImVtYWlsIjoiYnJpYW5kckBuYXZlci5jb20iLCJpYXQiOjE2NjIwNDM2MTUsImV4cCI6MTY2MjA0NzIxNSwiaXNzIjoicGxheXRvZ2V0aGVyIn0.Phab8jYHKefFPajA2BmKQZ6L7Hjkpy5VQ79xGjyFVF8")
    @POST("crew/register")
    suspend fun postRegisterCrew(
        @Body requestRegisterCrew: RequestRegisterCrew
    ): ResponseRegisterCrew

    //동아리 생성
    @Headers("Content-Type:application/json", "Authorization:eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjE2OSIsImVtYWlsIjoiYnJpYW5kckBuYXZlci5jb20iLCJpYXQiOjE2NjIwNDM2MTUsImV4cCI6MTY2MjA0NzIxNSwiaXNzIjoicGxheXRvZ2V0aGVyIn0.Phab8jYHKefFPajA2BmKQZ6L7Hjkpy5VQ79xGjyFVF8")
    @POST("crew")
    suspend fun postMakeCrew(
        @Body requestMakeCrew: RequestMakeCrew
    ) : ResponseMakeCrew

    //가입한 동아리 리스트
    @GET("crew/list")
    suspend fun getCrewList() : ResponseGetList

    //유저 닉네임 중복확인
    @GET("user/crew/{crewId}")
    suspend fun getNickNameDuplication(
        @Path("crewId") crewId : Int,
        @Query("nickname") nickname : String
    ) : ResponseGetNickNameDuplication

    //멀티 프로필 등록
    @Headers("Content-Type:application/json", "Authorization:eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjE2OSIsImVtYWlsIjoiYnJpYW5kckBuYXZlci5jb20iLCJpYXQiOjE2NjIwNDM2MTUsImV4cCI6MTY2MjA0NzIxNSwiaXNzIjoicGxheXRvZ2V0aGVyIn0.Phab8jYHKefFPajA2BmKQZ6L7Hjkpy5VQ79xGjyFVF8")
    @PUT("user/{crewId}")
    suspend fun putAddProfile(
        @Body requestAddProfile: RequestAddProfile,
        @Path("crewId") crewId : Int
    ) : ResponseAddProfile


}
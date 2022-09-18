package com.playtogether_android.data.api.userInfo

import com.playtogether_android.data.model.response.userInfo.ResBlockUserData
import com.playtogether_android.data.model.response.userInfo.ResMyInfoData
import com.playtogether_android.data.model.response.userInfo.ResOtherInfoData
import com.playtogether_android.data.singleton.PlayTogetherRepository
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserInfoService {
    // 유저 본인 멀티프로필 상세 조회
    @GET("user/{crewId}")
    suspend fun getMyInfo(
        @Path("crewId") crewId: Int = PlayTogetherRepository.crewId
    ): ResMyInfoData

    // 동아리원 멀티프로필 상세 조회
    @GET("user/{crewId}/{memberId}/profile")
    suspend fun getOtherInfo(
        @Path("crewId") crewId: Int = PlayTogetherRepository.crewId,
        @Path("memberId") memberId: Int
    ): ResOtherInfoData

    // 유저 차단
    @POST("user/block/{memberId}")
    suspend fun postBlockUser(
        @Path("memberId") memberId: Int
    ): ResBlockUserData


}
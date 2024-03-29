package com.playtogether_android.data.api.userInfo

import com.playtogether_android.data.model.ResGenericData
import com.playtogether_android.data.model.response.userInfo.*
import com.playtogether_android.data.singleton.PlayTogetherRepository
import okhttp3.MultipartBody
import retrofit2.http.*

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

    // 동아리 탈퇴
    @DELETE("crew/{crewId}")
    suspend fun delCrew(
        @Path("crewId") crewId: Int = PlayTogetherRepository.crewId
    ): ResGenericData

    // 유저 차단 리스트 조회
    @GET("user/block/list")
    suspend fun getBlockUserList(): ResBlockUserList

    // 유저 차단 해제
    @DELETE("user/unblock/{memberId}")
    suspend fun delUnblockUser(
       @Path("memberId") memberId: Int
    ) : ResUnblockUserData

    // 유저 멀티프로필 이미지 추가
    @Multipart
    @PUT("user/{crewId}/image")
    suspend fun putProfileImage (
        @Path("crewId") crewId: Int = PlayTogetherRepository.crewId,
        @Part image: MultipartBody.Part?
    ): ResGenericData


}
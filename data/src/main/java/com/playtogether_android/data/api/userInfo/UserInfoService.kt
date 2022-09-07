package com.playtogether_android.data.api.userInfo

import com.playtogether_android.data.model.response.userInfo.ResMyInfoData
import com.playtogether_android.data.singleton.PlayTogetherRepository
import retrofit2.http.GET
import retrofit2.http.Path

interface UserInfoService {
    // 유저 본인 멀티프로필 상세 조회
    @GET("user/{crewId}")
    suspend fun getMyInfo(
        @Path("crewId") crewId: Int = PlayTogetherRepository.crewId
    ): ResMyInfoData
}
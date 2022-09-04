package com.playtogether_android.data.api.mypage

import com.playtogether_android.data.model.response.light.ResponseLightJoinCancel
import com.playtogether_android.data.model.response.light.ResponseLightListCategory
import com.playtogether_android.data.model.response.mypage.ResponseUserCheck
import com.playtogether_android.data.model.response.mypage.ResponseUserDelete
import retrofit2.http.*

interface MyPageService {

    //유저 조회
    @GET("user/{userLoginId}")
    suspend fun getUserCheck(
        @Path("userLoginId") userLoginId: String,
    ): ResponseUserCheck

    @DELETE("auth/withdraw")
    suspend fun deletUser() : ResponseUserDelete
}
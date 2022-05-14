package com.playtogether_android.data.api.mypage

import com.playtogether_android.data.model.response.light.ResponseLightJoinCancel
import com.playtogether_android.data.model.response.light.ResponseLightListCategory
import com.playtogether_android.data.model.response.mypage.ResponseUserCheck
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface MyPageService {

    //유저 조회
    @GET("user/{userLoginId}")
    suspend fun getUserCheck(
        @Path("userLoginId") userLoginId: String,
    ): ResponseUserCheck
}
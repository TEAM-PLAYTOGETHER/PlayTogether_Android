package com.playtogether_android.data.api.home

import com.playtogether_android.data.model.response.home.ResponseJoinThunder
import com.playtogether_android.data.model.response.light.ResponseLightJoinCancel
import com.playtogether_android.data.model.response.light.ResponseLightListCategory
import com.playtogether_android.data.model.response.mypage.ResponseUserCheck
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface HomeService {

    //번개참여
    @POST("light/enter/{lightId}")
    suspend fun JoinThunder(
        @Path("lightId") lightId: Int,
    ): ResponseJoinThunder
}